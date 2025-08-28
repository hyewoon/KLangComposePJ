package com.hye.data.validate

import com.hye.domain.model.mlkit.ConfidenceLevel
import com.hye.domain.model.mlkit.HandWritingAnalysis
import javax.inject.Inject


class HandWritingValidator@Inject constructor(

) {

    fun validateHandWriting(candidates: List<String>): HandWritingAnalysis {
        val cleanWords = candidates.map { cleanWord(it) }
        val consistency = calculateConsistency(cleanWords)
        val level = determineConfidenceLevel(consistency)
        val mostFrequentWord = findMostFrequentWord(cleanWords)

        return HandWritingAnalysis(
            level = level,
            recognizedText = mostFrequentWord,
            candidates = cleanWords,
            consistency = consistency
        )
    }

    /*
    * mlkit 결과값으로 받은 후보군 중에서 공백, 숫자, 문장부호들을 제거
    * @param text 정리할 텍스트
    * @return 정리된 텍스트(빈 문자열이면 원본 trim 반환)
    * */
    private fun cleanWord(text: String): String {
        val result = text
            .replace(Regex("[\\s.,!?0-9]"), "")
            .trim()
        return result.ifEmpty { text.trim() }
    }

    /*
    * 정리된 단어들의 빈도를 계산해서 일관성 측정
    * @return 일관성 비율(0.0 ~1.0)
    * */
    private fun calculateConsistency(cleanWords: List<String>): Float {

        if(cleanWords.isEmpty()) return 0.0f
        //2. 가장 많은 단어 갯수 찾기
        val wordFrequency = cleanWords.groupingBy { it }.eachCount()
        val maxCount = wordFrequency.values.maxOf { it }
        //val mostWord = wordFrequency.maxByOrNull { it.value }?.key

        return maxCount.toFloat() / cleanWords.size

    }

    private fun findMostFrequentWord(cleanWords: List<String>): String {
        val wordFrequency = cleanWords.groupingBy { it }.eachCount()
        return wordFrequency.maxByOrNull { it.value }?.key ?: wordFrequency.keys.first()
    }

   private fun determineConfidenceLevel(consistency: Float): ConfidenceLevel {
       return when {
            consistency >= 0.7f -> ConfidenceLevel.HIGH
            consistency >= 0.5f ->ConfidenceLevel.MEDIUM
            else -> ConfidenceLevel.LOW
        }
    }
}

