package com.hye.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.hye.domain.model.AppLanguage
import com.hye.domain.repository.datastore.PreferencesDataStoreRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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
            preferences[PreferenceDataStoreConstants.DOCUMENT_ID] = id
        }
    }

    private fun getTodayDate(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }

    //날짜 바뀌면 오늘의 학습 Set 초기화
    override suspend fun checkDate() {
        dataSource.edit { preferences ->
            val lastStudyDate = preferences[PreferenceDataStoreConstants.LAST_STUDY_DATE] ?: ""
            val today = getTodayDate()

            if (lastStudyDate != today) {
                preferences[PreferenceDataStoreConstants.TODAY_STUDIED_WORDS]= emptySet()
                preferences[PreferenceDataStoreConstants.LAST_STUDY_DATE] = today
            }
        }
    }

    override suspend fun saveStudiedWord(word: String) {
        dataSource.edit { preferences ->
            val todaySet =
                preferences[PreferenceDataStoreConstants.TODAY_STUDIED_WORDS] ?: emptySet()
            preferences[PreferenceDataStoreConstants.TODAY_STUDIED_WORDS] = todaySet + word

            val totalSet = preferences[PreferenceDataStoreConstants.TOTAL_WORD] ?: emptySet()
            preferences[PreferenceDataStoreConstants.TOTAL_WORD] = totalSet + word
        }
    }

    override fun getTodayStudiedWord(): Flow<Set<String>> {
        return dataSource.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[PreferenceDataStoreConstants.TODAY_STUDIED_WORDS] ?: emptySet()
        }
    }

    private val totalWord: Flow<Set<String>> = dataSource.data.catch{
        if(it is IOException){
            emit(emptyPreferences())
        }else{
            throw it
        }
    }.map{preferences->
        preferences[PreferenceDataStoreConstants.TOTAL_WORD] ?: emptySet()

    }

    override fun getTotalWordCount():Flow<Int>{
        return totalWord.map{it.size}
    }


    override fun getTotalPoint(): Flow<Int> {
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
        dataSource.edit { preferences ->
            val totalPoint = preferences[PreferenceDataStoreConstants.TOTAL_POINT_COUNT] ?: 0
            preferences[PreferenceDataStoreConstants.TOTAL_POINT_COUNT] = totalPoint + 5
        }
    }

    override fun getLanguage(): Flow<AppLanguage> {
      return dataSource.data.map{preferences->
          val code =  preferences[PreferenceDataStoreConstants.LANGUAGE] ?:"en"
          AppLanguage.fromCode(code)
      }
    }

    override suspend fun saveLanguage(language: AppLanguage) {
        dataSource.edit{preferences->

            preferences[PreferenceDataStoreConstants.LANGUAGE]= language.code
        }
    }
}
