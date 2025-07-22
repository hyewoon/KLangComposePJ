package com.hye.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.hye.data.datasource.firestore.mapper.DomainToRoomMapper
import com.hye.data.datasource.firestore.mapper.RoomToDomainMapper
import com.hye.data.room.TargetWordDao
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.repository.roomdb.StudyRepository
import com.hye.domain.result.RoomResult
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudyRepositoryImpl @Inject constructor(
    private val dao: TargetWordDao,
    private val domainToRoomMapper: DomainToRoomMapper,
    private val roomToDomainMapper: RoomToDomainMapper
) : StudyRepository {

    override suspend fun insertStudyWords(words: List<TargetWordWithAllInfoEntity>)= runCatching {
        insertRoomDB(words)
        RoomResult.Success(Unit)
    }.getOrElse {
        RoomResult.RoomDBError(it)
    }

    override suspend fun getStudyWords(date: String): RoomResult<List<TargetWordWithAllInfoEntity>> = runCatching {
        val room = dao.searchTargetWordByDate(date).map{
            roomToDomainMapper.mapToDomain(it)
        }
        RoomResult.Success(room)
    }.getOrElse {
        RoomResult.RoomDBError(it)
    }

    override suspend fun getAllStudyWords(): RoomResult<List<TargetWordWithAllInfoEntity>> = runCatching{
        val room  = dao.getAllTargetWords().map{
            roomToDomainMapper.mapToDomain(it)
        }
        RoomResult.Success(room)
    }.getOrElse {
        RoomResult.RoomDBError(it)
    }

    override suspend fun deleteAllStudyWords(): RoomResult<Unit> = runCatching {
        dao.deleteAll()
        RoomResult.Success(Unit)
    }.getOrElse {
        RoomResult.RoomDBError(it)
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
