package com.hye.domain.repository.roomdb

import com.hye.domain.model.roomdb.BookMarkedWordWithAllInfoEntity
import com.hye.domain.result.AppResult
import kotlinx.coroutines.flow.Flow

interface BookMarkedWordsRepository {
    suspend fun insertBookMarkedWords(bookMarkedWords: BookMarkedWordWithAllInfoEntity): AppResult<Unit>
    suspend fun deleteBookMarkedWordByDocumentId(documentId: String): AppResult<Unit>
    suspend fun deleteAllBookMarkedWords():AppResult<Unit>
    fun getAllBookMarkedWords(): Flow<AppResult<List<BookMarkedWordWithAllInfoEntity>>>

}