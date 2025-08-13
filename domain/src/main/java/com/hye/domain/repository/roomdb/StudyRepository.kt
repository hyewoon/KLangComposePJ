package com.hye.domain.repository.roomdb

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.result.AppResult


interface StudyRepository {
    suspend fun insertStudyWords(words:List<TargetWordWithAllInfoEntity>): AppResult<Unit>
    suspend fun getStudyWords(date: String):AppResult<List<TargetWordWithAllInfoEntity>>
    suspend fun getAllStudyWords():AppResult<List<TargetWordWithAllInfoEntity>>
    suspend fun deleteAllStudyWords(): AppResult<Unit>
}