package com.hye.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.hye.data.datasource.firestore.mapper.DomainToRoomMapper
import com.hye.data.datasource.firestore.mapper.RoomToDomainMapper
import com.hye.data.room.TargetWordDao
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.repository.roomdb.StudyRepository
import com.hye.domain.result.AppResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudyRepositoryImpl @Inject constructor(
    private val dao: TargetWordDao,
    private val domainToRoomMapper: DomainToRoomMapper,
    private val roomToDomainMapper: RoomToDomainMapper,
) : StudyRepository {

    override suspend fun insertStudyWords(words: List<TargetWordWithAllInfoEntity>): AppResult<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                insertRoomDB(words)
                AppResult.Success(Unit)
            } catch (e: Exception) {
                AppResult.Failure(e.toString())
            }
        }
    }


    override fun getStudyWords(date: String): Flow<AppResult<List<TargetWordWithAllInfoEntity>>> =
        flow {
            try {
                dao.searchTargetWordByDate(date)
                    .map { roomList ->
                        roomList.map { roomEntity ->
                            roomToDomainMapper.mapToDomain(roomEntity)
                        }
                    }
                    .collect { domainList ->
                        println("getRoom성공")
                        emit(AppResult.Success(domainList))
                    }
            } catch (exception: Exception) {
                emit(AppResult.Failure(exception.toString()))
            }
        }


    override fun getAllStudyWords(): Flow<AppResult<List<TargetWordWithAllInfoEntity>>> = flow {
        try {
            dao.getAllTargetWords()
                .map { roomList ->
                    roomList.map { roomEntity ->
                        roomToDomainMapper.mapToDomain(roomEntity)
                    }
                }
                .collect { domainList ->
                    emit(AppResult.Success(domainList))
                }
        } catch (e: Exception) {
            emit(AppResult.Failure(e.toString()))
        }
    }

    override suspend fun getAllStudyWordsOnce(): AppResult<List<TargetWordWithAllInfoEntity>> {
        return withContext(Dispatchers.IO) {
            try {
               val result =  dao.getAllTargetWordsOnce()
                    .map {
                        roomToDomainMapper.mapToDomain(it)
                    }
                AppResult.Success(result)

            } catch (e: Exception) {
                AppResult.Failure(e.toString())
            }
        }
    }

    override suspend fun getStudyWordsOnce(date: String): AppResult<List<TargetWordWithAllInfoEntity>> {
        return withContext(Dispatchers.IO) {
            try {
               val result = dao.searchTargetWordByDateOnce(date)
                    .map {
                        roomToDomainMapper.mapToDomain(it)
                    }
                        AppResult.Success(result)

            } catch (e: Exception) {
                AppResult.Failure(e.toString())
            }
        }
    }
    override suspend fun deleteAllStudyWords(): AppResult<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                dao.deleteAll()
                AppResult.Success(Unit)
            } catch (e: Exception) {
                AppResult.Failure(e.toString())
            }
        }
    }

    override suspend fun updateBookmarkStatus(
        documentId: String,
        isBookmarked: Boolean,
        bookmarkedTimeStamp: Long,
    ): AppResult<Unit> {
        Log.d("Repository", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
        Log.d("Repository", "📝 UPDATE BOOKMARK STATUS")
        Log.d("Repository", "   documentId: '$documentId'")
        Log.d("Repository", "   isBookmarked: $isBookmarked")
        Log.d("Repository", "   timeStamp: $bookmarkedTimeStamp")

        return withContext(Dispatchers.IO) {
            try {
                Log.d("Repository", "   ⏳ Calling DAO...")
               val updatedRows =  dao.updateBookmarkAndNotify(
                   documentId = documentId,
                   isBookmarked= isBookmarked,
                   bookmarkedTimeStamp = bookmarkedTimeStamp)
                Log.d("Repository", "   ✅ DAO completed")
                Log.d("Repository", "   📊 Updated rows: $updatedRows")

                if (updatedRows == 0) {
                    Log.e("Repository", "   ⚠️ WARNING: No rows updated!")
                }

                // 검증
                delay(50)
                val verified = dao.getWordById(documentId)
                if(verified != null) {
                    Log.d("Repository", "   🔍 Verified word: ${verified.targetWord.korean}")
                    Log.d(
                        "Repository",
                        "   🔍 Verified: isBookmarked=${verified.targetWord.isBookmarked}"
                    )
                }else {
                    Log.e("Repository", "   ⚠️ Verified: Word not found!")
                }

                AppResult.Success(Unit)
            } catch (e: Exception) {
                Log.e("Repository", "   ❌ Exception: ${e.message}", e)
                Log.d("Repository", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
                AppResult.Failure(e.toString())
            }
        }
    }

    override fun getBookmarkedWords(isBookmarked: Boolean): Flow<AppResult<List<TargetWordWithAllInfoEntity>>> = flow {
        try {
            dao.getBookmarkedWords(isBookmarked)
                .map { targetWords ->
                    targetWords.map {
                        roomToDomainMapper.mapToDomain(it)
                    }
                }.collect {
                    emit(AppResult.Success(it))
                }
        } catch (e: Exception) {
            emit(AppResult.Failure(e.toString()))
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun insertRoomDB(words: List<TargetWordWithAllInfoEntity>) {
        val currentTime = System.currentTimeMillis()
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        words.forEach { word ->
            val targetWord = createTargetWord(word, currentTime, today)

            dao.insertLimitedTargetWordExampleInfo(
                targetWord = targetWord,
                exampleInfo = word.exampleInfo.map {
                    domainToRoomMapper.mapToRoomExampleInfo(it, targetWord.documentId)
                },
                pronunciationInfo = word.pronunciationInfo.map {
                    domainToRoomMapper.mapToRoomPronunciationInfo(it, targetWord.documentId)
                }
            )

        }
    }

    private fun createTargetWord(
        word: TargetWordWithAllInfoEntity,
        currentTime: Long,
        today: String,
    ) = domainToRoomMapper.mapToRoom(word).copy(
        timeStamp = currentTime,
        todayString = today,
        isBasicLearned = false,
        isListened = false,
        isExampleLearned = false,
        isWritten = false,
        isRecorded = false,
        isBookmarked = false,
        bookmarkedTimeStamp = 0L
    )
}






