package com.hye.data.datasource.firestore.dto

import com.google.firebase.firestore.PropertyName


/**
 * firestore에서 받아온 데이터 파싱
 * example은 3개로 갯수 제한
 *
 * 데이터 흐름
 * : firestore -> dto -> roomDB -> domain
 */
data class FireStoreDto (
    var documentId: String = "",
    var targetCode: Long = 0L,
    var frequency: Long = 0L,
    var korean: String ="",
    var english: String = "",
    var pos: String= "",
    var wordGrade: String = "",
    @PropertyName("example_info")
    var exampleInfo: List<FireStoreExampleInfoDto> = emptyList(),
    @PropertyName("pronunciation_info")
    var pronunciationInfo: List<FireStorePronunciationInfoDto> = emptyList()
){
    //example 정보중 필요하 갯수만 filter
    fun getFilteredExamples(type:String, limit:Int): List<FireStoreExampleInfoDto> {
        return exampleInfo.filter { it.type == type }.take(limit)
    }
}


data class FireStoreExampleInfoDto(
    @PropertyName("type")
    var type: String ="" ,
    @PropertyName("example")
    var example: String=""
)

data class FireStorePronunciationInfoDto(
    @PropertyName("pronunciation_info.pronunciation")
    var pronunciation: String = "",
    @PropertyName("pronunciation_info.link")
    var audioUrl: String = ""
)