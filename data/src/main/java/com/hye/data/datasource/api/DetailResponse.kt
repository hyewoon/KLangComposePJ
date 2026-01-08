package com.hye.data.datasource.api

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "detail_channel")
data class DetailChannelResponse(
    @Element(name = "item")
    val items: List<DetailItem> = listOf()
)

@Xml(name = "item")
data class DetailItem(
    @PropertyElement(name = "target_code")
    val targetCode: Int = 0,
    @Element(name = "word_info")
    val wordInfo: TargetWordInfo = TargetWordInfo()
)

@Xml(name = "word_info")
data class TargetWordInfo(
    @PropertyElement(name = "word")
    val word: String = "",
    @PropertyElement(name = "word_grade")
    val wordGrade: String = "",
    @PropertyElement(name = "pos")
    val pos: String = "",
    @Element(name = "sense_info")
    val senseInformation: List<SenseInformation>?= null,
)

@Xml(name = "sense_info")
data class SenseInformation(
    @PropertyElement(name = "sense_order")
    val senseOrder: Int = 0,
   @PropertyElement(name = "definition")
    val definition: String = "",
    @Element(name = "example_info")
    val exampleInfo: List<ExampleInfo>? = null
)

@Xml(name = "example_info")
data class ExampleInfo(
    @PropertyElement(name = "type")
    val type: String= "",
    @PropertyElement(name = "example")
    val example: String =""
)


