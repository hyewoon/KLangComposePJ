package com.hye.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.hye.data.datasource.firestore.mapper.DomainToRoomMapper
import com.hye.data.datasource.firestore.mapper.RoomToDomainMapper
import com.hye.data.room.TargetWordDao
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.repository.roomdb.StudyRepository
import com.hye.domain.result.AppResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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

    override suspend fun insertStudyWords(words: List<TargetWordWithAllInfoEntity>) = runCatching {
        insertRoomDB(words)
        println("insert성공")
        AppResult.Success(Unit)

    }.getOrElse {
        AppResult.Failure(it)
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
                emit(AppResult.Failure(exception))
            }
        }


    override fun getAllStudyWords(): Flow<AppResult<List<TargetWordWithAllInfoEntity>>> = flow {
        try {
            dao.getAllTargetWords()
                .map { roomEntity ->
                    roomEntity.map { roomEntity ->
                        roomToDomainMapper.mapToDomain(roomEntity)
                    }
                }
                .collect { domainList ->
                    emit(AppResult.Success(domainList))
                }
        } catch (e: Exception) {
            emit(AppResult.Failure(e))
        }
    }


    override suspend fun deleteAllStudyWords() = runCatching {
        dao.deleteAll()
        AppResult.Success(Unit)
    }.getOrElse {
        AppResult.Failure(it)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun insertRoomDB(words: List<TargetWordWithAllInfoEntity>) {
        words.forEach { words ->

            val currentTime = System.currentTimeMillis()
            val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

            var targetWord = domainToRoomMapper.mapToRoom(words)
            targetWord = targetWord.copy(
                timeStamp = currentTime,
                todayString = today,
                isBasicLearned = false,
                isListened = false,
                isExampleLearned = false,
                isWritten = false,
                isRecorded = false
            )

            dao.insertLimitedTargetWordExampleInfo(
                targetWord = targetWord,
                exampleInfo = words.exampleInfo.map {
                    domainToRoomMapper.mapToRoomExampleInfo(
                        it,
                        targetWord.documentId
                    )
                } ?: emptyList(),
                pronunciationInfo = words.pronunciationInfo.map {
                    domainToRoomMapper.mapToRoomPronunciationInfo(
                        it,
                        targetWord.documentId
                    )
                } ?: emptyList()
            )


        }
    }
}
