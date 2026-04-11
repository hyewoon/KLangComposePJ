package com.hye.domain.model



enum class AppLanguage(val code: String){

    ENGLISH("en"),
    KOREAN("ko");

    /*
    * Language 타입 전체에 대한 유틸리티 함수
    * values() enum의 모든 값을 배열로 반환해주는 함수(enum에서 자동으로 제공)
    * */
    companion object {
        fun fromCode(code: String): AppLanguage {
            return entries.find { it.code == code } ?: ENGLISH
        }
    }
}