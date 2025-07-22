package com.hye.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
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

    override fun <T> readPreference(keyName: String, defaultValue: T): Flow<T> =
        dataSource.data.catch() { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {
            when (defaultValue) {
                is Boolean -> it[booleanPreferencesKey(keyName)] ?: defaultValue
                is Int -> it[intPreferencesKey(keyName)] ?: defaultValue
                is String -> it[stringPreferencesKey(keyName)] ?: defaultValue
                else -> throw IllegalArgumentException("Unsupported preference type")
            } as T

        }


    override suspend fun <T> writePreference(keyName: String, value: T) {
        dataSource.edit {
            when (value) {
                is Boolean -> it[booleanPreferencesKey(keyName)] = value
                is Int -> it[intPreferencesKey(keyName)] = value
                is String -> it[stringPreferencesKey(keyName)] = value
                else -> throw IllegalArgumentException("Unsupported preference type")

            }
        }
    }

    override suspend fun <T> deletePreference(keyName: String) {
        dataSource.edit {
            it.remove(booleanPreferencesKey(keyName))
            it.remove(intPreferencesKey(keyName))
            it.remove(stringPreferencesKey(keyName))
        }
    }

    /*firestore 단어다운로드
    **/
    override val documentId: Flow<String> = readPreference(PreferenceDataStoreConstants.DOCUMENT_ID_KEY, "")

    suspend fun saveDocumentId(id: String) {
        writePreference(PreferenceDataStoreConstants.DOCUMENT_ID_KEY, id)
    }

    override val targetCode: Flow<Int> = readPreference(PreferenceDataStoreConstants.TARGET_CODE_KEY, 0)

    private suspend fun saveTargetCode(code: Int) {
        writePreference(PreferenceDataStoreConstants.TARGET_CODE_KEY, code)
    }


    /*
    * 오늘의 학습 관련
    * */
    override val targetWordCount: Flow<Int> = readPreference(PreferenceDataStoreConstants.TARGET_WORD_COUNT_KEY, 10)

    private suspend fun saveTargetWordCount(count: Int) {
        writePreference(PreferenceDataStoreConstants.TARGET_WORD_COUNT_KEY, count)
    }

    override val currentWordCount: Flow<Int> = readPreference(PreferenceDataStoreConstants.CURRENT_WORD_COUNT_KEY, 1)

    private suspend fun saveCurrentWordCount(count: Int) {
        writePreference(PreferenceDataStoreConstants.CURRENT_WORD_COUNT_KEY, count)
    }

}