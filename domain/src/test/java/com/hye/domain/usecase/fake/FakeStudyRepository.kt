package com.hye.domain.usecase.fake

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.repository.roomdb.StudyRepository
import com.hye.domain.result.AppResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeStudyRepository(
    initialWords: List<TargetWordWithAllInfoEntity> = emptyList())
    : StudyRepository {

    // Room에 저장되어 있는 단어 (메모리에 들고 있음)
    val storedWords = initialWords.toMutableList()

    // ---- 상황 만들기: null이면 정상 동작, 값을 넣으면 그 결과를 반환 ----
    var getAllResult: AppResult<List<TargetWordWithAllInfoEntity>>? = null
    var deleteResult: AppResult<Unit>? = null
    var insertResult: AppResult<Unit>? = null

    // ---- 결과 관찰 ----
    var deleteCalled = false
        private set
    var insertCalled = false
        private set
    var insertedWords: List<TargetWordWithAllInfoEntity> = emptyList()
        private set

    override suspend fun getAllStudyWords(): AppResult<List<TargetWordWithAllInfoEntity>> =
        getAllResult ?: AppResult.Success(storedWords.toList())

    override suspend fun getStudyWords(date: String): AppResult<List<TargetWordWithAllInfoEntity>> =
        AppResult.Success(storedWords.filter { it.todayString == date })

    override suspend fun deleteOldAndNonBookmarkedWords(date: String): AppResult<Unit> {
        deleteCalled = true
        deleteResult?.let { return it }
        storedWords.removeAll { it.todayString != date && !it.isBookmarked }
        return AppResult.Success(Unit)
    }

    override suspend fun insertStudyWords(words: List<TargetWordWithAllInfoEntity>): AppResult<Unit> {
        insertCalled = true
        insertResult?.let { return it }
        insertedWords = words
        storedWords.addAll(words)
        return AppResult.Success(Unit)
    }

    // ---- 북마크: 이번 UseCase 테스트에서는 쓰지 않으므로 단순하게 구현 ----
    override suspend fun updateBookmarkStatus(
        documentId: String,
        isBookmarked: Boolean,
        bookmarkedTimeStamp: Long,
    ): AppResult<Unit> {
        val index = storedWords.indexOfFirst { it.documentId == documentId }
        if (index >= 0) {
            storedWords[index] = storedWords[index].copy(
                isBookmarked = isBookmarked,
                bookmarkedTimeStamp = bookmarkedTimeStamp,
            )
        }
        return AppResult.Success(Unit)
    }

    override fun getBookmarkedWords(isBookmarked: Boolean): Flow<AppResult<List<TargetWordWithAllInfoEntity>>> =
        flowOf(AppResult.Success(storedWords.filter { it.isBookmarked == isBookmarked }))



}