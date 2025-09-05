package com.hye.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow


/*
* 북마크
* */
@Dao
interface BookMarkedWordDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookMarkedWords(bookMarkedWords: BookMarkedWords)

    @Transaction
    suspend fun insertBookMarkedWordsWithAllInfo(
        bookMarkedWords: BookMarkedWords,
        targetWord: TargetWord,
        exampleInfo: List<TargetWordExampleInfo>,
        pronunciationInfo: List<TargetWordPronunciationInfo>,
    ) {
        insertBookMarkedWords(bookMarkedWords)


    }

    @Query("SELECT * FROM bookmarked_words ORDER BY bookMarkTime DESC")
    fun getAllBookMarkedWords(): Flow<List<BookMarkedWordsWithAllInfo>>


    @Transaction
    @Query("DELETE FROM bookmarked_words WHERE bookmarked_documentId_fk = :documentId")
    suspend fun deleteBookMarkedWordByDocumentId(documentId: String)

    @Transaction
    @Query("DELETE FROM bookmarked_words")
    suspend fun deleteAllBookMarkedWords()

    /*
    * 북마크 상태가 변경되는것을 감지
    * */
    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_words WHERE bookmarked_documentId_fk = :documentId)")
    fun isBookMarked(documentId: String): Flow<Boolean>
}