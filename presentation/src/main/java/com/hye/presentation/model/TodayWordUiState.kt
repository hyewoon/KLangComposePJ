package com.hye.presentation.model


import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.model.roomdb.WordExampleInfoEntity
import java.util.Collections.emptyList


/*

//combine(a, b) 두개의 flow를 결합
todayWordsUiState,-> WordList
currentIndex, -> currentIndex
)
  init {
        viewModelScope.launch {

            _currentWord.value = UiStateResult.Loading
            combine(
                todayWordsUiState,
                currentIndex,
            ) { todayWordsUiState, currentIndex ->
                when (todayWordsUiState) {
                    is UiStateResult.Success -> {
                        val wordItem = state.data.getOrNull(currentIndex) ?: TodayWordsUiState()
                        _pronunciationUrl.value = wordItem.pronunciation
                        UiStateResult.Success(wordItem)
                    }
    }

 */
data class TodayWordUiState(
    val wordList: List<TargetWordWithAllInfoEntity> = emptyList(),
    val currentIndex: Int = 0,
    val snackBarMessage: String = "",
    //오늘 학습한 단어를 저장 하는 용도
    val todayStudiedWords: Set<String> = emptySet(),
    //누적 학습 단어 count
    val totalStudiedWords: Int = 0,
    //북마크 단어
    val isBookmarked: Boolean = false,

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
    val currentWordId: String
        get() = currentWord.documentId
    //오늘 학습한 단어의 갯수
    val todayStudiedWordCount: Int
        get()= todayStudiedWords.size

    }

