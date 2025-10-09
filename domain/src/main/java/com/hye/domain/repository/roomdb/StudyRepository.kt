package com.hye.domain.repository.roomdb

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.result.AppResult
import kotlinx.coroutines.flow.Flow


interface StudyRepository {
    suspend fun insertStudyWords(words: List<TargetWordWithAllInfoEntity>): AppResult<Unit>
    suspend fun deleteAllStudyWords(): AppResult<Unit>
    fun getStudyWords(date: String): Flow<AppResult<List<TargetWordWithAllInfoEntity>>>
    fun getAllStudyWords(): Flow<AppResult<List<TargetWordWithAllInfoEntity>>>
    suspend fun getAllStudyWordsOnce(): AppResult<List<TargetWordWithAllInfoEntity>>
    suspend fun getStudyWordsOnce(date: String): AppResult<List<TargetWordWithAllInfoEntity>>

    //북마크 관련
    suspend fun updateBookmarkStatus(documentId: String, isBookmarked: Boolean, bookmarkedTimeStamp: Long): AppResult<Unit>
    fun getBookmarkedWords(): Flow<AppResult<List<TargetWordWithAllInfoEntity>>>
}