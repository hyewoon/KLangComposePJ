package com.hye.domain.repository.firestore

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity


interface FireStoreRepository {
 suspend fun getStudyWordFromFireStore(count: Long): List<TargetWordWithAllInfoEntity>
}