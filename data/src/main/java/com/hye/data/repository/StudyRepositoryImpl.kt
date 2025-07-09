package com.hye.data.repository

import com.hye.data.datasource.firestore.mapper.ToDomainMapper
import com.hye.data.datasource.firestore.mapper.ToRoomDBMapper
import com.hye.data.room.TargetWordDao
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.repository.roomdb.StudyRepository
import com.hye.domain.result.RoomDBResult
import javax.inject.Inject

class StudyRepositoryImpl @Inject constructor(
    private val targetWordDao: TargetWordDao,
    private val toRoomDBMapper: ToRoomDBMapper,
    private val toDomainMapper: ToDomainMapper,
): StudyRepository {

    override suspend fun createStudyRoom(word: List<TargetWordWithAllInfoEntity>): RoomDBResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun readStudyRoom(date: String): RoomDBResult<List<TargetWordWithAllInfoEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun readAllStudyRoom(): RoomDBResult<List<TargetWordWithAllInfoEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllStudyRoom(): RoomDBResult<Unit> {
        TODO("Not yet implemented")
    }
}