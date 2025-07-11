package com.hye.domain.model.roomdb

data class TargetWordEntity(
    val documentId: String = "",
    val targetCode: Long = 0L,
    val frequency: Long = 0L,
    val korean: String = "",
    val english: String = "",
    val pos: String = "",
    val wordGrade: String = "",
    val exampleInfo: List<TargetWordExampleInfoEntity> = listOf(),
    val pronunciationInfo: List<TargetWordPronunciationInfoEntity> = listOf()
)


data class TargetWordExampleInfoEntity(
    val type: String = "",
    val example: String = ""
)


data class TargetWordPronunciationInfoEntity(
    val pronunciation: String = "",
    val audioUrl: String = ""
)