package com.hye.data.datasource.firestore.mapper

import com.hye.data.datasource.firestore.dto.FireStoreDto
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.model.roomdb.WordExampleInfoEntity
import com.hye.domain.model.roomdb.WordPronunciationInfoEntity


/*
* dto-> domain
* */
class DtoToDomainMapper {

    fun mapToDomain(dto: FireStoreDto) = TargetWordWithAllInfoEntity(
        documentId = dto.documentId,
        targetCode = dto.targetCode,
        frequency = dto.frequency,
        korean = dto.korean,
        english = dto.english,
        wordGrade = dto.wordGrade,
        pos = dto.pos,
        exampleInfo = dto.exampleInfo.map {
            WordExampleInfoEntity(
                type = it.type,
                example = it.example
            )
        },
        pronunciationInfo = dto.pronunciationInfo.map {
            WordPronunciationInfoEntity(
                pronunciation = it.pronunciation,
                audioUrl = it.audioUrl

            )

        }
    )
}
