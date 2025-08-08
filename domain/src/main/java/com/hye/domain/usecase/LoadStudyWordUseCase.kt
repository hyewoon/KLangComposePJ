package com.hye.domain.usecase

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.repository.roomdb.StudyRepository
import com.hye.domain.repository.firestore.FireStoreRepository
import com.hye.domain.result.RoomResult
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


/*
*
* usecase는 함수 하나만
* hilt _ usecase 테스트코드하기 쉽다는 목적있음
* 도메인에는 ineject코드들어가면 안된다.
* test 코드 추가 하기
* */

/*
* usecase 비즈니스 로직 : 단어가져오기
* buttom클릭 후
* 1. 오늘 날짜에 해당하는 room데이터 존재하는지 확인
* 2. 데이터 존재 x(room이 비어있음) : firstore에서 다운 후 room저장해서 가져오기
* 3. 데이터 존재 0 & 오늘 날짜 x : 기존 데이터 삭제 후, firestore에서 다운 후 room에 저장해서 가져오기
* 4. 데이터 존재 0 & 오늘 날짜 0 : room에서 가져오기
*
* */

class LoadStudyWordUseCase(
    private val studyRepository: StudyRepository,
    private val firestoreRepository: FireStoreRepository,
) {
    suspend operator fun invoke(count: Int): RoomResult<List<TargetWordWithAllInfoEntity>> {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA))
        val count = count.toLong()

        // Result처리 먼저
        val result = studyRepository.getAllStudyWords()
        if (result !is RoomResult.Success) return result  // 접근 실패시 바로 반환

        //데이터가 아예 존재하지 않거나, 데이터가 존재하지만 오늘 날짜에 해당하는 데이터가 없는 경우
        val roomData = result.data.isEmpty() || result.data.none { it.todayString == today }

        return if (roomData) {
            //firestore에서 받아오기 + room저장
            if(result.data.isNotEmpty()) studyRepository.deleteAllStudyWords()

            val firestoreResult =  firestoreRepository.getStudyWordFromFireStore(count)
            studyRepository.insertStudyWords(firestoreResult)
            studyRepository.getStudyWords(today)

        } else {
            studyRepository.getStudyWords(today)
        }
    }
}
