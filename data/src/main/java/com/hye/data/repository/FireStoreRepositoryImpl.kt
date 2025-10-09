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
    private val preference: PreferencesDataStoreManager,
    private val fireStore: FirebaseFirestore,
    private val dtoToDomainMapper: DtoToDomainMapper,
) : FireStoreRepository {
    override suspend fun getStudyWordFromFireStore(count: Long): List<TargetWordWithAllInfoEntity> =
        runCatching {

            preference.saveDocumentId("")

            val lastDocId : String = preference.documentId.first()

            val snapshot = getFireStoreData(lastDocId, count)

            if (snapshot.documents.isNotEmpty()) {
                val lastDocumentId = snapshot.documents.last().id
                preference.saveDocumentId(lastDocumentId)
            }

            val dtoList = snapshot.documents.map { mapToTargetWordDto(it) } //snapShot을 FirestoreDto로 변환

           dtoList.map { dtoToDomainMapper.mapToDomain(it) }// FirestoreDto를 DomainEntity로 변환

        }.getOrElse { exception ->
            exception.printStackTrace()
            emptyList()

        }

    private suspend fun getFireStoreData(lastDocId: String, count: Long): QuerySnapshot {
        val snapshot = if (lastDocId.isEmpty()) {
            fireStore.collection("words1")
                .orderBy("frequency", Query.Direction.DESCENDING)
                .orderBy("korean", Query.Direction.ASCENDING)
                .limit(count)

        } else {
            val lastDocRef = fireStore.collection("words1").document(lastDocId)
            val lastDoc = lastDocRef.get().await()
            println("Found last document: ${lastDoc.exists()}, ID: ${lastDoc.id}")

            fireStore.collection("words1")
                .orderBy("frequency", Query.Direction.DESCENDING)
                .orderBy("korean", Query.Direction.ASCENDING)
                .startAfter(lastDoc) // 마지막 문서 다음부터 시작
                .limit(count)

        }
            .get()
            .await()
        return snapshot
    }


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
).also {
    println("documentId: ${it.documentId}")
    println("exampleInfo: ${it.exampleInfo}")
    println("pronunciationInfo: ${it.pronunciationInfo}")
}

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