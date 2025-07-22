package com.hye.data.datasource.firestore.mapper


import com.hye.data.room.TargetWordWithAllInfo
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.model.roomdb.WordExampleInfoEntity
import com.hye.domain.model.roomdb.WordPronunciationInfoEntity

/*
* roomDB- > domain
* */
class RoomToDomainMapper {
    fun mapToDomain(room: TargetWordWithAllInfo): TargetWordWithAllInfoEntity {
        return TargetWordWithAllInfoEntity(
            documentId = room.targetWord.documentId,
            targetCode = room.targetWord.targetCode,
            frequency = room.targetWord.frequency,
            korean = room.targetWord.korean,
            english = room.targetWord.english,
            wordGrade = room.targetWord.wordGrade,
            pos = room.targetWord.pos,
            timeStamp = room.targetWord.timeStamp,
            todayString = room.targetWord.todayString,
            exampleInfo = room.exampleInfo.map {
                WordExampleInfoEntity(
                    type = it.type,
                    example = it.example
                )
            },
            pronunciationInfo = room.pronunciationInfo.map {
                WordPronunciationInfoEntity(
                    pronunciation = it.pronunciation,
                    audioUrl = it.audioUrl
                )


            }
        )
    }

}