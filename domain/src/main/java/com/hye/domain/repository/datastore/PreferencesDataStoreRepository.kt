package com.hye.domain.repository.datastore

import kotlinx.coroutines.flow.Flow


interface PreferencesDataStoreRepository {

    val targetCode : Flow<Int>
    val documentId : Flow<String>
    val targetWordCount : Flow<Int>
    val currentWordCount : Flow<Int>

    fun <T> readPreference(keyName:String, defaultValue: T): Flow<T>
    suspend fun <T> writePreference(keyName:String, value: T)
    suspend fun <T> deletePreference(keyName:String)





}