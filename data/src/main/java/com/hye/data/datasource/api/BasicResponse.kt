package com.hye.data.datasource.api

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "basicChannel")
data class BasicChannelResponse(
    @Element(name="item")
    val item: List<WordInfo>? = null
)
@Xml(name = "item")
data class WordInfo(
    @PropertyElement(name="target_code")
    val targetCode: String = "",
    @PropertyElement(name="word")
    val word: String = "",
    @PropertyElement(name="word_grade")
    val wordGrade: String? =null,
    @PropertyElement(name="pos")
    val pos: String="",
    @Element(name="sense")
    val sense: List<Sense> = emptyList()
)

@Xml(name="sense")
data class Sense(
    @PropertyElement(name="sense_order")
    val senseOrder: Int = 0,
    @PropertyElement(name="definition")
    val definition: String ="",
    @Element(name="translation")
   val translation: Translation?=null,
)
@Xml(name="translation")
data class Translation(
    @PropertyElement(name="trans_word")
    val transWord : String? =null,
    @PropertyElement(name="trans_dfn")
    val transDfn: String? =null
)

