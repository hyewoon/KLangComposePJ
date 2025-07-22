package com.hye.domain.repository.roomdb

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.result.RoomResult


interface StudyRepository {
    suspend fun insertStudyWords(words:List<TargetWordWithAllInfoEntity>): RoomResult<Unit>
    suspend fun getStudyWords(date: String):RoomResult<List<TargetWordWithAllInfoEntity>>
    suspend fun getAllStudyWords():RoomResult<List<TargetWordWithAllInfoEntity>>
    suspend fun deleteAllStudyWords(): RoomResult<Unit>
}