package com.hye.domain.model.roomdb

data class TargetWordWithAllInfoEntity(
    val documentId: String = "",
    val targetCode: Long = 0L,
    val frequency: Long = 0L,
    val korean: String = "",
    val english: String = "",
    val pos: String = "",
    val wordGrade: String = "등급 없음",
    val timeStamp: Long= 0L,
    val todayString: String ="",
    val exampleInfo: List<WordExampleInfoEntity> = emptyList(),
    val pronunciationInfo: List<WordPronunciationInfoEntity> = emptyList(),
    val isBookMarked: Boolean = false

    )

data class WordExampleInfoEntity(
    val type: String = "",
    val example: String = "",
)

data class WordPronunciationInfoEntity(
    val pronunciation: String = "",
    val audioUrl: String = "",
)

data class BookMarkedWordWithAllInfoEntity(
    val bookmarkedId: Int= 0,
    val bookMarKedTime: Long = 0L,
    val createdDate: String = "",
    val documentId: String = "",
    val targetCode: Long = 0L,
    val frequency: Long = 0L,
    val korean: String = "",
    val english: String = "",
    val pos: String = "",
    val wordGrade: String = "등급 없음",
    val timeStamp: Long= 0L,
    val todayString: String ="",
    val exampleInfo: List<WordExampleInfoEntity> = emptyList(),
    val pronunciationInfo: List<WordPronunciationInfoEntity> = emptyList()
)
