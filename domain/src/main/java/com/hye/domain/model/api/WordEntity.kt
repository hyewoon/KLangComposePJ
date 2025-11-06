package com.hye.domain.model.api



data class WordEntity(
    val targetCode: String = "",
    val word: String = "",
    val supNo: Int = 0,
    val wordGrade: String = "등급없음",
    val pos: String = "",
    val sense: List<SenseInfo> = mutableListOf()
)

data class SenseInfo(
    val senseOrder: Int = 0,
    val transWord: String = "",
    val definition: String = "",
    val transDfn: String = "",
)