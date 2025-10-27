package com.hye.domain.repository.datastore

import kotlinx.coroutines.flow.Flow

interface PreferencesDataStoreRepository {

    suspend fun getDocumentId(): Flow<String>
    suspend fun saveDocumentId(id: String)

    suspend fun getTargetWordCount( ): Flow<Int>
    suspend fun saveTargetWordCount()

    suspend fun getTotalWordCount(): Flow<Int>
    suspend fun incrementTotalWordCount()

    suspend fun getTotalPoint(): Flow<Int>
    suspend fun addTotalPoint()





}