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
    val isBookmarked: Boolean = false,
    val bookmarkedTimeStamp: Long = 0L

    )

data class WordExampleInfoEntity(
    val type: String = "",
    val example: String = "",
)

data class WordPronunciationInfoEntity(
    val pronunciation: String = "",
    val audioUrl: String = "",
)


