package com.hye.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

/**
 * 개별 테이블별로 데이터 insert하고 , 그걸 바탕으로 AllInfo entity에 데이터를 넣고,
 * 전체 데이터 불러올때는 AllInfo entity를 가져오기
 * @ inject 대신에  미리 hilt가 dao 가지고 있음
 */

@Dao
interface TargetWordDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTargetWord(targetWord: TargetWord)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTargetWordExampleInfo(exampleInfo: List<TargetWordExampleInfo>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTargetWordPronunciationInfo(pronunciationInfo: List<TargetWordPronunciationInfo>)

    @Transaction
    suspend fun insertLimitedTargetWordExampleInfo(
        targetWord: TargetWord,
        exampleInfo:List<TargetWordExampleInfo>,
        pronunciationInfo: List<TargetWordPronunciationInfo>,
    ) {
        insertTargetWord(targetWord)

        //제한된 갯수의 데이터만 가져와서, firestore에서 받은 데이터와 일치시키기
        val allowedTypes = setOf("문장", "구")
        val limitedExamples = exampleInfo
            .groupBy { it.type in allowedTypes }
            .mapValues { (_, examples) -> examples.take(3) }
            .values
            .flatten()
            .take(6)

        insertTargetWordExampleInfo(limitedExamples)
        insertTargetWordPronunciationInfo(pronunciationInfo)
    }


    @Transaction
    suspend fun insertTargetWordWithAllInfo(
        targetWord: TargetWord,
        exampleInfo: List<TargetWordExampleInfo>,
        pronunciationInfo: List<TargetWordPronunciationInfo>,
    ) {

        insertTargetWord(targetWord)
        insertTargetWordExampleInfo(exampleInfo)
        insertTargetWordPronunciationInfo(pronunciationInfo)
    }

    @Transaction
    @Query("DELETE FROM pronunciation_info WHERE pronunciation_documentId_fk = :documentId")
    suspend fun deletePronunciationInfo(documentId: String)

    @Transaction
    @Query("DELETE FROM example_info WHERE example_documentId_fk = :documentId")
    suspend fun deleteExampleInfo(documentId: String)

    /*
    * 과제 북마크 되지 않은 단어들은 삭제
    * 오늘의 단어는 유지
    * */
    @Transaction
    @Query("DELETE FROM target_word WHERE isBookmarked = 0 AND todayString < :date")
    suspend fun deleteOldAndNonBookmarkedWords(date: String)

    @Transaction
    @Query("SELECT* FROM target_word WHERE todayString = :todayString")
    fun searchTargetWordByDate(todayString: String): Flow<List<TargetWordWithAllInfo>>

    @Transaction
    @Query("SELECT* FROM target_word WHERE todayString = :todayString")
    suspend fun searchTargetWordByDateOnce(todayString: String): List<TargetWordWithAllInfo>

    @Transaction
    @Query("SELECT * FROM target_word")
    fun getAllTargetWords(): Flow<List<TargetWordWithAllInfo>>

    @Transaction
    @Query("SELECT * FROM target_word")
    suspend fun getAllTargetWordsOnce(): List<TargetWordWithAllInfo>

    /*
    * 북마크 상태 업데이트
    * */
    @Transaction
    @Query("UPDATE target_word SET isBookmarked = :isBookmarked , bookmarkedTimeStamp = :bookmarkedTimeStamp WHERE documentId = :documentId")
    suspend fun updateBookmarkStatus(documentId: String, isBookmarked: Boolean, bookmarkedTimeStamp: Long) : Int


    @Query("SELECT * FROM target_word WHERE documentId = :documentId")
    suspend fun getWordById(documentId: String): TargetWordWithAllInfo?


    @Transaction
    @Query("SELECT * FROM target_word WHERE isBookmarked = :isBookmarked ORDER BY bookmarkedTimeStamp DESC" )
     fun getBookmarkedWords( isBookmarked : Boolean): Flow<List<TargetWordWithAllInfo>>


    // 🔥 추가: Once 버전 (Flow 트리거용)
    @Transaction
    @Query("SELECT * FROM target_word WHERE isBookmarked = :isBookmarked ORDER BY bookmarkedTimeStamp DESC")
    suspend fun getBookmarkedWordsOnce(isBookmarked: Boolean): List<TargetWordWithAllInfo>

    // 🔥 수정: 여러 쿼리로 모든 Flow 트리거
    @Transaction
    suspend fun updateBookmarkAndNotify(
        documentId: String,
        isBookmarked: Boolean,
        bookmarkedTimeStamp: Long
    ): Int {
        val result = updateBookmarkStatus(documentId, isBookmarked, bookmarkedTimeStamp)

        // 🔥 여러 쿼리 실행으로 모든 Flow 트리거
        if (result > 0) {
            getWordById(documentId)
            getAllTargetWordsOnce()
            getBookmarkedWordsOnce(true)
            getBookmarkedWordsOnce(false)
        }

        return result
    }

}
