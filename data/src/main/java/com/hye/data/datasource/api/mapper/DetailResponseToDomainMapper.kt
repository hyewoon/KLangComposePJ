package com.hye.data.datasource.api.mapper

import com.hye.data.datasource.api.DetailItem
import com.hye.data.datasource.api.ExampleInfo
import com.hye.data.datasource.api.SenseInformation
import com.hye.domain.model.api.DetailWordEntity

class DetailResponseToDomainMapper {

    fun mapToDomain(word: DetailItem): DetailWordEntity {
        return DetailWordEntity(
            targetCode = word.targetCode,
            word = word.wordInfo.word,
            wordGrade = word.wordInfo.wordGrade,
            pos = word.wordInfo.pos,
            senses = word.wordInfo.senseInformation.map {sense->
                SenseInformation(
                   senseOrder = sense.senseOrder,
                    definition = sense.definition,
                    exampleInfo = sense.exampleInfo.map {example->
                        ExampleInfo(
                            type = example.type,
                            example = example.example
                        )
                    }
                )
            },

        )

    }
}