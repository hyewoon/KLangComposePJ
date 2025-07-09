package com.hye.data.datasource.firestore.mapper


import com.hye.data.datasource.firestore.dto.FireStoreDto
import com.hye.data.datasource.firestore.dto.FireStoreExampleInfoDto
import com.hye.data.datasource.firestore.dto.FireStorePronunciationInfoDto
import com.hye.data.room.TargetWord
import com.hye.data.room.TargetWordExampleInfo
import com.hye.data.room.TargetWordPronunciationInfo


/*
* firestore -> roomDB
* */
class ToRoomDBMapper {

    class ToRoomDbMapper {
        fun mapToRoom(dto: FireStoreDto): TargetWord {
            return TargetWord(
                documentId = dto.documentId,
                targetCode = dto.targetCode,
                frequency = dto.frequency,
                korean = dto.korean,
                english = dto.english,
                pos = dto.pos,
                wordGrade = dto.wordGrade
            )
        }

        fun mapToRoomExampleInfo(
            dto: FireStoreExampleInfoDto,
            documentId: String,
        ) = TargetWordExampleInfo(
            documentId = documentId,
            type = dto.type,
            example = dto.example
        )

        fun mapToRoomPronunciationInfo(
            dto: FireStorePronunciationInfoDto,
            documentId: String,
        ) = TargetWordPronunciationInfo(
            documentId = documentId,
            pronunciation = dto.pronunciation,
            audioUrl = dto.audioUrl
        )

    }

}