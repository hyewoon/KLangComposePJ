package com.hye.domain.repository.roomdb

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.result.RoomDBResult

interface StudyRepository {
    suspend fun createStudyRoom(word: List<TargetWordWithAllInfoEntity>) : RoomDBResult<Unit>
    suspend fun readStudyRoom(date: String):RoomDBResult<List<TargetWordWithAllInfoEntity>>
    suspend fun readAllStudyRoom(): RoomDBResult<List<TargetWordWithAllInfoEntity>>
    suspend fun deleteAllStudyRoom(): RoomDBResult<Unit>
}