package com.hye.data.datasource.firestore.mapper

import com.hye.data.room.BookMarkedWords
import com.hye.data.room.TargetWord
import com.hye.data.room.TargetWordExampleInfo
import com.hye.data.room.TargetWordPronunciationInfo
import com.hye.domain.model.roomdb.BookMarkedWordWithAllInfoEntity
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.model.roomdb.WordExampleInfoEntity
import com.hye.domain.model.roomdb.WordPronunciationInfoEntity

/*
* domain -> room저장용
* */
class DomainToRoomMapper {

    /*
    * domainTo TargetWordWithAllInfoEntity
    * */
    fun mapToRoom(domain: TargetWordWithAllInfoEntity) = TargetWord(
        documentId = domain.documentId,
        targetCode = domain.targetCode,
        frequency = domain.frequency,
        korean = domain.korean,
        english = domain.english,
        pos = domain.pos,
        wordGrade = domain.wordGrade,
    )

    fun mapToRoomExampleInfo(domain: WordExampleInfoEntity, documentId: String) =
        TargetWordExampleInfo(
            documentId = documentId,
            type = domain.type,
            example = domain.example
        )

    fun mapToRoomPronunciationInfo(domain: WordPronunciationInfoEntity, documentId: String) =
        TargetWordPronunciationInfo(
            documentId = documentId,
            pronunciation = domain.pronunciation,
            audioUrl = domain.audioUrl
        )

    /*
    * domainTo BookMarkedWordWithAllInfoEntity
    * */
    fun mapToRoomBookMarked(domain: BookMarkedWordWithAllInfoEntity) = BookMarkedWords(
        bookmarkedId = domain.bookmarkedId,
        documentId = domain.documentId,
        bookMarkTime = domain.timeStamp,
        createdDate = domain.todayString
    )

    fun mapToRoomTargetWord(domain: BookMarkedWordWithAllInfoEntity) = TargetWord(
        documentId = domain.documentId,
        targetCode = domain.targetCode,
        frequency = domain.frequency,
        korean = domain.korean,
        english = domain.english,
        pos = domain.pos,
        wordGrade = domain.wordGrade,
        timeStamp = domain.timeStamp,
        todayString = domain.todayString,
        isBookMarked = true,
    )

}