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
}