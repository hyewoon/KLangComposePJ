package com.hye.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.hye.domain.repository.datastore.PreferencesDataStoreRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

//top-level declare to keep DataStore as a singleton
val Context.dataStore: DataStore<Preferences> by preferencesDataStore("dataStore")

@Singleton
class PreferencesDataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context,
) : PreferencesDataStoreRepository {

    private val dataSource = context.dataStore

    override suspend fun getDocumentId(): Flow<String> {
        return dataSource.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[PreferenceDataStoreConstants.DOCUMENT_ID] ?: ""
        }
    }

    override suspend fun saveDocumentId(id: String) {
        dataSource.edit { preferences ->
            preferences[PreferenceDataStoreConstants.DOCUMENT_ID] ?: ""
        }
    }

    override suspend fun getTargetWordCount(): Flow<Int> {
        return dataSource.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[PreferenceDataStoreConstants.TODAY_TARGET_WORD_COUNT] ?: 0
        }
    }

    override suspend fun saveTargetWordCount() {
        dataSource.edit { preferences ->
            preferences[PreferenceDataStoreConstants.TODAY_TARGET_WORD_COUNT] ?: 0
        }
    }

    override suspend fun getTotalWordCount(): Flow<Int> {
        return dataSource.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }

        }.map { preferences ->
            preferences[PreferenceDataStoreConstants.TOTAL_WORD_COUNT] ?: 0
        }
    }

    override suspend fun incrementTotalWordCount() {
        dataSource.edit { preferences ->
            val totalWord = preferences[PreferenceDataStoreConstants.TOTAL_WORD_COUNT] ?: 0
            preferences[PreferenceDataStoreConstants.TOTAL_WORD_COUNT] = totalWord + 1
        }
    }

    override suspend fun getTotalPoint(): Flow<Int> {
        return dataSource.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }

        }.map { preferences ->
            preferences[PreferenceDataStoreConstants.TOTAL_POINT_COUNT] ?: 0
        }
    }

    override suspend fun addTotalPoint() {
        dataSource.edit{ preferences->
            val totalPoint = preferences[PreferenceDataStoreConstants.TOTAL_POINT_COUNT] ?: 0
            preferences[PreferenceDataStoreConstants.TOTAL_POINT_COUNT] = totalPoint + 5
        }
    }


}