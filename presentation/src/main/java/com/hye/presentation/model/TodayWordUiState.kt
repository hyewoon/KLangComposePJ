package com.hye.presentation.model

import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.model.roomdb.WordExampleInfoEntity

data class TodayWordUiState(
    val wordList: List<TargetWordWithAllInfoEntity> = emptyList(),
    val currentIndex: Int = 0,
    val snackBarMessage: String = "",
    val bookMarkedIndices: Set<Int> = emptySet(),
) {
    //계산 속성
    val currentWord: TargetWordWithAllInfoEntity
        get() = if (wordList.isNotEmpty() && currentIndex in wordList.indices) wordList[currentIndex] else TargetWordWithAllInfoEntity()

    val currentWordExample: WordExampleInfoEntity
        get() = if(currentWord.exampleInfo.isNotEmpty()) currentWord.exampleInfo[0] else WordExampleInfoEntity()

    val hasNext: Boolean
        get() = wordList.isNotEmpty() && currentIndex < wordList.size - 1
    val hasPrevious: Boolean
        get() = currentIndex > 0
    val isMarked: Boolean
        get() = bookMarkedIndices.contains(currentIndex)

    }

