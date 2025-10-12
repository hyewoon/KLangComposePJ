package com.hye.presentation.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.repository.roomdb.StudyRepository
import com.hye.domain.result.AppResult
import com.hye.domain.usecase.LoadStudyWordUseCase
import com.hye.presentation.model.TodayWordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/*init {
    viewModelScope.launch {
        //sharedViewModel.resetDailyWordCount()

        _currentWord.value = UiStateResult.Loading
        combine(
            todayWordsUiState,
            currentIndex,
        ) { state, index ->
            when (state) {
                is UiStateResult.Success -> {
                    Log.d("HomeViewModel", "combine값 받음")
                    val wordItem = state.data.getOrNull(index) ?: TodayWordsUiState()
                    _pronunciationUrl.value = wordItem.pronunciation
                    Log.d("HomeViewModel", "wordItem: ${wordItem.pronunciation}")
                    UiStateResult.Success(wordItem)
                }
                //다른 경우 기본값 반환
                is UiStateResult.Loading -> {
                    UiStateResult.Loading
                }*/
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadStudyWordUseCase: LoadStudyWordUseCase,
    private val roomRepository: StudyRepository,
) : ViewModel() {

    private val _todayWordUiState = MutableStateFlow(TodayWordUiState())
    val todayWordUiState: StateFlow<TodayWordUiState> = _todayWordUiState.asStateFlow()

    init{
        loadStudyWord(10)
    }


    fun loadStudyWord(count: Int) {
        viewModelScope.launch {
            loadStudyWordUseCase(count).collectLatest { roomResult ->
                when (roomResult) {
                    is AppResult.Loading -> {
                        }

                    is AppResult.Success -> {
                        _todayWordUiState.update {
                            it.copy(
                                wordList = roomResult.data, //실제 데이터 넣기
                                snackBarMessage = ""
                            )
                        }
                    }

                    is AppResult.Failure -> {
                        _todayWordUiState.update {
                            it.copy(
                                snackBarMessage = roomResult.exception.toString()
                                    ?: "Unknown error"
                            )
                        }
                    }


                    is AppResult.NoConstructor -> {}
                }
            }
        }


    }


    fun moveToNext() {
        val currentState = _todayWordUiState.value
        if (currentState.hasNext) {
            _todayWordUiState.update {
                it.copy(currentIndex = it.currentIndex + 1)
            }
        } else {
            _todayWordUiState.update {
                it.copy(snackBarMessage = "마지막 단어입니다.")
            }
        }
    }

    fun moveToPrevious() {
        val currentState = _todayWordUiState.value
        if (currentState.hasPrevious) {
            _todayWordUiState.update {
                it.copy(currentIndex = it.currentIndex - 1)
            }
        } else {
            _todayWordUiState.update {
                it.copy(snackBarMessage = "첫번째 단어입니다.")
            }
        }
    }


    fun clearSnackBarMessage() {
        _todayWordUiState.update {
            it.copy(snackBarMessage = "")
        }
    }

    fun toggleBookmark(id: String, currentBookmarkState: Boolean) {
        viewModelScope.launch {
            val newBookmarkState = !currentBookmarkState
            val timeStamp = if (newBookmarkState) System.currentTimeMillis() else 0L

            roomRepository.updateBookmarkStatus(
                documentId= id,
                isBookmarked = newBookmarkState,
                bookmarkedTimeStamp = timeStamp
            )
        }

    }
}
