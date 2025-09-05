package com.hye.data.repository

import com.hye.data.datasource.firestore.mapper.DomainToRoomMapper
import com.hye.data.datasource.firestore.mapper.RoomToDomainMapper
import com.hye.data.room.BookMarkedWordDao
import com.hye.data.room.TargetWordDao
import com.hye.domain.model.roomdb.BookMarkedWordWithAllInfoEntity
import com.hye.domain.repository.roomdb.BookMarkedWordsRepository
import com.hye.domain.result.AppResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class BookMarkedWordsRepositoryImpl @Inject constructor(
    private val bookMarkedDao: BookMarkedWordDao,
    private val targetWordDao: TargetWordDao,
    private val domainToRoomMapper: DomainToRoomMapper,
    private val roomToDomainMapper: RoomToDomainMapper

): BookMarkedWordsRepository {
    override suspend fun insertBookMarkedWords(bookMarkedWords: BookMarkedWordWithAllInfoEntity)= runCatching {


/*        val roomBookmark = domainToRoomMapper.mapToRoomBookMarked(bookMarkedWords)
        val roomTargetWord = domainToRoomMapper.mapToRoom(bookMarkedWords.targetWordWithAllInfo)


        // 북마크 추가
        bookMarkedDao.insertBookMarkedWords(roomBookmark)

        // TargetWord의 북마크 상태 업데이트
        targetWordDao.updateBookMarkStatus(roomBookmark.documentId, true)
        */

        insertRoomDB(bookMarkedWords)
        AppResult.Success(Unit)
    }.getOrElse {
        AppResult.Failure(it)
    }

    override suspend fun deleteBookMarkedWordByDocumentId(documentId: String) = runCatching {
        bookMarkedDao.deleteBookMarkedWordByDocumentId(documentId)
        AppResult.Success(Unit)
    }.getOrElse {
        AppResult.Failure(it)
    }

    override suspend fun deleteAllBookMarkedWords() = runCatching{
        bookMarkedDao.deleteAllBookMarkedWords()
        AppResult.Success(Unit)
    }.getOrElse {
        AppResult.Failure(it)
    }

    override fun getAllBookMarkedWords(): Flow<AppResult<List<BookMarkedWordWithAllInfoEntity>>> = flow {
        try {
            bookMarkedDao.getAllBookMarkedWords()
                .map { roomEntity ->
                    roomEntity.map {
                        roomToDomainMapper.mapToDomainBookMarkedMapper(it)
                    }
                }
                .collect {
                    emit(AppResult.Success(it))
                }

        } catch (e: Exception) {
            emit(AppResult.Failure(e))
        }
    }

    private suspend fun insertRoomDB(bookMarkedWords: BookMarkedWordWithAllInfoEntity) {
        val bookMarkedTime = System.currentTimeMillis()
        val createDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        var bookMarkedWord = domainToRoomMapper.mapToRoomBookMarked(bookMarkedWords)
        bookMarkedWord = bookMarkedWord.copy(
            bookMarkTime = bookMarkedTime,
            createdDate = createDate
        )
        val targetWord = domainToRoomMapper.mapToRoomTargetWord(bookMarkedWords)
        val exampleInfo = bookMarkedWords.exampleInfo.map {
            domainToRoomMapper.mapToRoomExampleInfo(it, bookMarkedWord.documentId)
        }
        val pronunciationInfo = bookMarkedWords.pronunciationInfo.map {
            domainToRoomMapper.mapToRoomPronunciationInfo(it, bookMarkedWord.documentId)

        }

        //RoomDB에 저장
        targetWordDao.insertLimitedTargetWordExampleInfo(targetWord, exampleInfo, pronunciationInfo)
        bookMarkedDao.insertBookMarkedWords(bookMarkedWord)

    }
}


