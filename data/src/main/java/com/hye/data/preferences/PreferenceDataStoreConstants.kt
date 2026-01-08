package com.hye.data.preferences

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object PreferenceDataStoreConstants {

    //단어 다운 로드 관련(firestore)
    val DOCUMENT_ID = stringPreferencesKey("document_id")
    /*
    * 단어 학습 관련
    * CURRENT_WORD_COUNT: 현재 학습 한 단어 ID set
    * */
    val TODAY_STUDIED_WORDS = stringSetPreferencesKey("today_studied_words")
    val TOTAL_WORD = stringSetPreferencesKey("total_word")
    val LAST_STUDY_DATE= stringPreferencesKey("last_study_date")

    /*
    * 포인트 관련
    * */
    val TOTAL_POINT_COUNT = intPreferencesKey("total_point_count")

    /*
    언어 설정
     */
    val LANGUAGE =stringPreferencesKey("language")






}