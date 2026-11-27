package com.hye.domain.usecase

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.result.AppResult
import com.hye.domain.usecase.fake.FakeFireStoreRepository
import com.hye.domain.usecase.fake.FakeStudyRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import java.time.LocalDate
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertNull

class LoadStudyWordUseCaseTest {


    private val today = LocalDate.of(2026, 11, 27)
    private val todayString = "2026-11-27"
    private val yesterdayString = "2026-11-26"

    private fun word(targetCode: Long, date: String = todayString, bookmarked: Boolean = false) =
        TargetWordWithAllInfoEntity(
            documentId = targetCode.toString(),
            targetCode = targetCode,
            korean = "단어$targetCode",
            todayString = date,
            isBookmarked = bookmarked,
        )

    private fun createUseCase(
        study: FakeStudyRepository,
        firestore: FakeFireStoreRepository,
    ) = LoadStudyWordUseCase(
        studyRepository = study,
        firestoreRepository = firestore,
        todayProvider = { today },
    )

    // 정책 1. 오늘 단어가 Room에 있으면 Firestore를 호출하지 않는다.
    @Test
    fun `오늘 단어가 이미 있으면 Firestore를 호출하지 않는다`() = runTest {
        val study = FakeStudyRepository(initialWords = listOf(word(1), word(2)))
        val firestore = FakeFireStoreRepository()

        val result = createUseCase(study, firestore)(10)

        assertEquals(0, firestore.fetchCallCount)
        assertIs<AppResult.Success<List<TargetWordWithAllInfoEntity>>>(result)
        assertEquals(2, result.data.size)
    }

    // 정책 2. 조회와 저장이 성공하면 마지막 단어의 targetCode를 커서로 저장한다.
    @Test
    fun `조회와 저장이 성공하면 마지막 단어의 targetCode를 커서로 저장한다`() = runTest {
        val study = FakeStudyRepository()
        val firestore = FakeFireStoreRepository().apply {
            wordsToReturn = listOf(word(101), word(102), word(103))
        }

        val result = createUseCase(study, firestore)(10)

        assertIs<AppResult.Success<List<TargetWordWithAllInfoEntity>>>(result)
        assertTrue(study.insertCalled)
        assertEquals(103L, firestore.savedTargetCode)
    }

    // 정책 3. Room 저장이 실패하면 커서를 저장하지 않는다. (이번에 고친 버그)
    @Test
    fun `Room 저장이 실패하면 커서를 저장하지 않는다`() = runTest {
        val study = FakeStudyRepository().apply {
            insertResult = AppResult.Failure(RuntimeException("insert failed"))
        }
        val firestore = FakeFireStoreRepository().apply {
            wordsToReturn = listOf(word(101), word(102))
        }

        val result = createUseCase(study, firestore)(10)

        assertIs<AppResult.Failure>(result)
        assertNull(firestore.savedTargetCode)
    }

    // 정책 3-1. 삭제가 실패해도 저장과 커서 저장을 하지 않는다.
    @Test
    fun `이전 단어 삭제가 실패하면 저장도 커서 저장도 하지 않는다`() = runTest {
        val study = FakeStudyRepository().apply {
            deleteResult = AppResult.Failure(RuntimeException("delete failed"))
        }
        val firestore = FakeFireStoreRepository().apply {
            wordsToReturn = listOf(word(101))
        }

        val result = createUseCase(study, firestore)(10)

        assertIs<AppResult.Failure>(result)
        assertFalse(study.insertCalled)
        assertNull(firestore.savedTargetCode)
    }

    // 정책 4. Firestore 조회가 실패하면 삭제도 저장도 하지 않고 Failure를 반환한다.
    @Test
    fun `Firestore 조회가 실패하면 기존 단어를 지우지 않고 Failure를 반환한다`() = runTest {
        val yesterdayWord = word(1, date = yesterdayString)
        val study = FakeStudyRepository(initialWords = listOf(yesterdayWord))
        val firestore = FakeFireStoreRepository().apply {
            errorToThrow = RuntimeException("network error")
        }

        val result = createUseCase(study, firestore)(10)

        assertIs<AppResult.Failure>(result)
        assertFalse(study.deleteCalled)
        assertFalse(study.insertCalled)
        assertNull(firestore.savedTargetCode)
        assertTrue(yesterdayWord in study.storedWords) // 어제 단어가 그대로 남아 있음
    }

    // 정책 5. 받은 단어가 없으면 Success(emptyList())를 반환하고 커서는 그대로 둔다.
    @Test
    fun `받은 단어가 없으면 빈 리스트로 성공을 반환하고 커서를 저장하지 않는다`() = runTest {
        val study = FakeStudyRepository()
        val firestore = FakeFireStoreRepository() // wordsToReturn 기본값이 빈 리스트

        val result = createUseCase(study, firestore)(10)

        assertIs<AppResult.Success<List<TargetWordWithAllInfoEntity>>>(result)
        assertTrue(result.data.isEmpty())
        assertFalse(study.deleteCalled)
        assertNull(firestore.savedTargetCode)
    }

    // 정책 6. 취소되면 Failure가 아니라 CancellationException이 그대로 전파된다.
    @Test
    fun `취소되면 Failure로 바꾸지 않고 CancellationException을 전파한다`() = runTest {
        val study = FakeStudyRepository()
        val firestore = FakeFireStoreRepository().apply {
            errorToThrow = CancellationException("screen closed")
        }

        assertFailsWith<CancellationException> {
            createUseCase(study, firestore)(10)
        }
        assertFalse(study.insertCalled)
        assertNull(firestore.savedTargetCode)
    }

}