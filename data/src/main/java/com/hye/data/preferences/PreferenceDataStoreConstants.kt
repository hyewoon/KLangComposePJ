package com.hye.data.preferences

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceDataStoreConstants {

   /*
   * domain 모듈용
   * */
    const val TARGET_CODE_KEY = "target_code"
    const val DOCUMENT_ID_KEY = "document_id"
    const val TARGET_WORD_COUNT_KEY = "target_word_count"
    const val CURRENT_WORD_COUNT_KEY = "current_word_count"

    /*
    * 실제 dataStore사용
    * */

    //단어 다운 로드 관련(firestore)
    val TARGET_CODE =intPreferencesKey(TARGET_CODE_KEY)
    val DOCUMENT_ID = stringPreferencesKey(DOCUMENT_ID_KEY)

    /*
    * 단어학습 관련
    * TARGET_WORD_COUNT: 오늘 학습 할 단어 수
    * CURRENT_WORD_COUNT: 현재 학습 한 단어 수
    * */
    val TARGET_WORD_COUNT = intPreferencesKey(TARGET_WORD_COUNT_KEY)
    val CURRENT_WORD_COUNT = intPreferencesKey(CURRENT_WORD_COUNT_KEY)

    /*
    * 포인트 관련
    *  PAW_POINT(누적): 현재 까지 학습한 단어 수
    *  TARGET_POINT(누적): 현재 까지 적립된 포인트
    * */
    val PAW_POINT = intPreferencesKey("paw_point")
    val TARGET_POINT = intPreferencesKey("target_point")

    /*
    * PUSH_NOTIFICATION: 알림 설정
    *  LANGUAGE_SETTING: 언어 설정
    * */
    val PUSH_NOTIFICATION = booleanPreferencesKey("push_notification")
    val LANGUAGE_SETTING: Preferences.Key<String> = stringPreferencesKey("language_setting")



}