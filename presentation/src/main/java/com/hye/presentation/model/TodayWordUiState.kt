package com.hye.presentation.model

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.model.roomdb.WordExampleInfoEntity

data class TodayWordUiState(
    val wordList: List<TargetWordWithAllInfoEntity> = emptyList(),
    val currentIndex: Int = 0,
    val isLoading: Boolean = false,
    val error: String = "",
    val showLastWordMessage: Boolean = false,
    val showFirstWordMessage: Boolean = false
    ) {
    //계산 속성
    val currentWord: TargetWordWithAllInfoEntity
        get() = wordList[currentIndex]
    val currentWordExample: WordExampleInfoEntity
        get() = currentWord.exampleInfo[0]
    val hasNext: Boolean
        get() = currentIndex <= wordList.size - 1
    val hasPrevious: Boolean
        get() = currentIndex >= 0
}
