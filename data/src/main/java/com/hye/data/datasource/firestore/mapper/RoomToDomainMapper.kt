package com.hye.data.datasource.firestore.mapper


import android.util.Log
import com.hye.data.room.TargetWordWithAllInfo
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.model.roomdb.WordExampleInfoEntity
import com.hye.domain.model.roomdb.WordPronunciationInfoEntity

/*
* roomDB- > domain
* */
class RoomToDomainMapper {
    // 카운터 추가
    private var exampleInfoCreateCount = 0
    private var pronunciationInfoCreateCount = 0
    private var mapToDomainCallCount = 0
    private var mapToDomainBookMarkedCallCount = 0

    fun mapToDomain(room: TargetWordWithAllInfo): TargetWordWithAllInfoEntity {
        mapToDomainCallCount++
        Log.d("Mapper", "mapToDomain 호출 횟수: $mapToDomainCallCount")

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
                exampleInfoCreateCount++
                Log.d("Mapper", "WordExampleInfoEntity 생성 횟수: $exampleInfoCreateCount")

                WordExampleInfoEntity(
                    type = it.type,
                    example = it.example
                )
            },
            pronunciationInfo = room.pronunciationInfo.map {
                pronunciationInfoCreateCount++
                Log.d("Mapper", "WordPronunciationInfoEntity 생성 횟수: $pronunciationInfoCreateCount")

                WordPronunciationInfoEntity(
                    pronunciation = it.pronunciation,
                    audioUrl = it.audioUrl
                )


            }
        )
    }


}