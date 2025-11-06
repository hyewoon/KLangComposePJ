package com.hye.data.datasource.api.mapper

import com.hye.data.datasource.api.WordInfo
import com.hye.domain.model.api.SenseInfo
import com.hye.domain.model.api.WordEntity

class BasicResponseToDomainMapper {

    fun mapToDomain(word: WordInfo) : WordEntity {
        return WordEntity(
            targetCode = word.targetCode,
            word = word.word,
            wordGrade = word.wordGrade,
            pos = word.pos,
            sense = word.sense.map {
                SenseInfo(
                   senseOrder = it.senseOrder,
                    definition = it.definition,
                    transWord = it.translation.transWord,
                    transDfn = it.translation.transDfn
                )
            }

        )
    }
}