package com.hye.domain.repository.datastore

import com.hye.domain.model.AppLanguage
import kotlinx.coroutines.flow.Flow

interface PreferencesDataStoreRepository {

    suspend fun getDocumentId(): Flow<String>
    suspend fun saveDocumentId(id: String)
    suspend fun checkDate()
    fun getTodayStudiedWord(): Flow<Set<String>>
    suspend fun saveStudiedWord(word: String)
    fun getTotalPoint(): Flow<Int>
    fun getTotalWordCount(): Flow<Int>
    suspend fun addTotalPoint()
     fun getLanguage(): Flow<AppLanguage>
    suspend fun saveLanguage(language: AppLanguage)

}
