package com.hye.data.preferences

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceDataStoreConstants {

    //단어 다운 로드 관련(firestore)
    val DOCUMENT_ID = stringPreferencesKey("document_id")

    /*
    * 단어학습 관련
    * TODAY_TARGET_WORD_COUNT: 오늘 학습 할 단어 수
    * CURRENT_WORD_COUNT: 현재 학습 한 단어 수
    * */
    val TODAY_TARGET_WORD_COUNT = intPreferencesKey("today_target_word_count")
    val CURRENT_WORD_COUNT = intPreferencesKey("current_word_count")

    /*
    * 포인트 관련
    *  PAW_POINT(누적): 현재 까지 학습한 단어 수
    *  TARGET_POINT(누적): 현재 까지 적립된 포인트
    * */
    val TOTAL_WORD_COUNT = intPreferencesKey("total_word_count")
    val TOTAL_POINT_COUNT = intPreferencesKey("total_point_count")




}