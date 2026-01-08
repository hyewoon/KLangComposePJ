package com.hye.domain.model.api



data class DetailWordEntity(
    val targetCode: Int = 0,
    val word: String = "",
    val pos: String = "",
    val wordGrade: String = "",
    val senses: List<SenseInformation> = mutableListOf(),

    )
data class SenseInformation(
    val senseOrder: Int = 0,
    val definition: String = "",
    val exampleInfo: List<ExampleInfo> = mutableListOf()
)

data class ExampleInfo(
    val example: String = "",
    val type: String = "",
)
