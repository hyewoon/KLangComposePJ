package com.hye.data.repository

import kotlinx.coroutines.flow.first
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.hye.data.datasource.firestore.dto.FireStoreDto
import com.hye.data.datasource.firestore.dto.FireStoreExampleInfoDto
import com.hye.data.datasource.firestore.dto.FireStorePronunciationInfoDto
import com.hye.data.datasource.firestore.mapper.DtoToDomainMapper
import com.hye.data.preferences.PreferencesDataStoreManager
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.repository.firestore.FireStoreRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FireStoreRepositoryImpl @Inject constructor(
    private val preferences: PreferencesDataStoreManager,
    private val fireStore: FirebaseFirestore,
    private val dtoToDomainMapper: DtoToDomainMapper,
) : FireStoreRepository {

    override suspend fun getStudyWordFromFireStore(count: Long): List<TargetWordWithAllInfoEntity> {
        //preferences.saveDocumentId("")
        val lastTargetCode = preferences.getLastTargetCode().first()
        val snapshot = getFireStoreData(lastTargetCode, count)

        val words = snapshot.documents
            .map { mapToTargetWordDto(it) }//snapShot을 FirestoreDto로 변환
            .map { dtoToDomainMapper.mapToDomain(it) }// FirestoreDto를 DomainEntity로 변환

        return words
    }

    override suspend fun saveLastTargetCode(targetCode: Long) {
        preferences.saveLastTargetCode(targetCode)
    }

    private suspend fun getFireStoreData(lastTargetCode: Long?, count: Long): QuerySnapshot {
        val baseQuery = fireStore.collection("words1")
            .orderBy("frequency", Query.Direction.DESCENDING)
            .orderBy("korean", Query.Direction.ASCENDING)

        val query = if (lastTargetCode == null) {
            baseQuery
        } else {
            val lastDoc = fireStore.collection("words1")
                .whereEqualTo("targetCode", lastTargetCode)   // 0번에서 확인한 필드 이름으로
                .limit(1)
                .get()
                .await()
                .documents
                .firstOrNull()
                ?: throw IllegalStateException("기준 단어가 존재하지 않음: $lastTargetCode")

            baseQuery.startAfter(lastDoc)
        }

        return query.limit(count).get().await()

    }

    private fun mapToTargetWordDto(doc: DocumentSnapshot) = FireStoreDto(
        documentId = doc.id,
        targetCode = doc.getLong("targetCode") ?: 0L,
        frequency = doc.getLong("frequency") ?: 0L,
        korean = doc.getString("korean") ?: "",
        english = doc.getString("english") ?: "",
        wordGrade = doc.getString("wordGrade") ?: "",
        pos = doc.getString("pos") ?: "",

        exampleInfo = parseExampleInfo(doc),
        pronunciationInfo = parsePronunciationInfo(doc)
    )

    @Suppress("UNCHECKED_CAST")
    private fun parseExampleInfo(doc: DocumentSnapshot) = runCatching {
        val exampleInfo =
            doc.get("example_info") as? List<Map<String, Any>> ?: emptyList<Map<String, Any>>()
        exampleInfo.map { example ->
            FireStoreExampleInfoDto(
                type = example["type"] as? String ?: "",
                example = example["example"] as? String ?: ""
            )
        }
    }.onFailure {
        println("Error parsing example_info: ${it.message}")

    }.getOrDefault(emptyList())

    @Suppress("UNCHECKED_CAST")
    private fun parsePronunciationInfo(doc: DocumentSnapshot) = runCatching {
        val pronunciationInfo =
            doc.get("pronunciation_info") as? List<Map<String, Any>>
                ?: emptyList<Map<String, Any>>()
        pronunciationInfo.map { pronunciation ->
            FireStorePronunciationInfoDto(
                pronunciation = pronunciation["pronunciation_info.pronunciation"] as? String
                    ?: "",
                audioUrl = pronunciation["pronunciation_info.link"] as? String ?: ""
            )
        }
    }.onFailure {
        println("Error parsing pronunciation_info: ${it.message}")

    }.getOrDefault(emptyList())

}