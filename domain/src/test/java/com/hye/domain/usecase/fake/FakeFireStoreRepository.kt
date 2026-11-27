package com.hye.domain.usecase.fake

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.repository.firestore.FireStoreRepository

class FakeFireStoreRepository : FireStoreRepository {
    // ---- 상황 만들기 ----
    var wordsToReturn: List<TargetWordWithAllInfoEntity> = emptyList()
    var errorToThrow: Exception? = null

    // ---- 결과 관찰 ----
    var fetchCallCount = 0
        private set
    var savedTargetCode: Long? = null   // null이면 커서가 저장되지 않은 것
        private set

    override suspend fun getStudyWordFromFireStore(count: Long): List<TargetWordWithAllInfoEntity> {
        fetchCallCount++
        errorToThrow?.let { throw it }   // await()에서 예외가 던져지는 상황을 흉내 냄
        return wordsToReturn.take(count.toInt())
    }

    override suspend fun saveLastTargetCode(targetCode: Long) {
        savedTargetCode = targetCode
    }
}