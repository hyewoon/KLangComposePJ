package com.hye.domain.usecase

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.repository.roomdb.StudyRepository
import com.hye.domain.repository.firestore.FireStoreRepository
import com.hye.domain.result.AppResult
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.coroutines.cancellation.CancellationException


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


/*       // Result처리 먼저
val result = studyRepository.getAllStudyWords()
if (result !is AppResult.Success) return result  // 접근 실패시 바로 반환

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
}*/

class LoadStudyWordUseCase(
    private val studyRepository: StudyRepository,
    private val firestoreRepository: FireStoreRepository,
) {

    private var lastInsertDate: String? = null

    suspend operator fun invoke(count: Int): AppResult<List<TargetWordWithAllInfoEntity>> {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA))
        val countLong = count.toLong()

        return try {
            //1. 캐시 확인
            if (lastInsertDate == today) {
                return studyRepository.getStudyWords(today)
            }

            //2. Room에 오늘 데이터가 있는지 확인
            val roomResult = studyRepository.getAllStudyWords()
            if(roomResult is AppResult.Failure) {
                return AppResult.Failure(roomResult.exception)
            }
            val todayData =
                (roomResult as AppResult.Success).data.filter { it.todayString == today }

            //오늘 날짜 해당하는 데이터 존재-> 그 값 가져오기
            if (todayData.isNotEmpty()) {
                lastInsertDate = today
                return studyRepository.getStudyWords(today)
            }
            //3. Firestore 조회를 먼저(실패하면 기존 데이터는 그대로 남음)
            val words = firestoreRepository.getStudyWordFromFireStore(countLong)//단어받아오기
            if (words.isEmpty()) {
                return AppResult.Success(emptyList())
            }

            //4. 조회가 성공한 뒤에 삭제 + 저장
            val deleteResult = studyRepository.deleteOldAndNonBookmarkedWords(today)
            if (deleteResult is AppResult.Failure) {
                return AppResult.Failure(deleteResult.exception)
            }
            val insertResult = studyRepository.insertStudyWords(words)
            if (insertResult is AppResult.Failure) {
                return AppResult.Failure(insertResult.exception)
            }
            //5.저장 성공 → 커서 보장(
            firestoreRepository.saveLastTargetCode(words.last().targetCode)

            lastInsertDate = today
            studyRepository.getStudyWords(today)
        } catch (e: CancellationException) {
            throw e                       // 취소는 삼키지 않고 다시 던짐
        } catch (e: Exception) {
            AppResult.Failure(e)
        }
    }
}


