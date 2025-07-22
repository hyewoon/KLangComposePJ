package com.hye.domain.usecase

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.repository.roomdb.StudyRepository
import com.hye.domain.repository.firestore.FireStoreRepository
import com.hye.domain.result.RoomResult
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject


class LoadStudyWordUseCase @Inject constructor(
    private val studyRepository: StudyRepository,
    private val firestoreRepository: FireStoreRepository,
) {
    suspend operator fun invoke(count: Int): RoomResult<List<TargetWordWithAllInfoEntity>> {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA))
        val count = count.toLong()

        // Result처리 먼저
        val result = studyRepository.getAllStudyWords()
        if (result !is RoomResult.Success) return result  // 접근 실패시 바로 반환

        val roomData = result.data.isEmpty() || result.data.none { it.todayString == today }

        return if (roomData) {
            fetchAndInsertData(count)
            studyRepository.getStudyWords(today)
        } else studyRepository.getStudyWords(today)
    }

    private suspend fun fetchAndInsertData(count: Long) {
        val result = firestoreRepository.getStudyWordFromFireStore(count)
        studyRepository.insertStudyWords(result)
    }


}
