package com.hye.presentation.ui.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.repository.roomdb.BookMarkedWordsRepository
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

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadStudyWordUseCase: LoadStudyWordUseCase,
    private val roomRepository: StudyRepository,
    private val bookMarkRepository: BookMarkedWordsRepository
) : ViewModel() {

    private val _todayWordUiState = MutableStateFlow(TodayWordUiState())
    val todayWordUiState: StateFlow<TodayWordUiState> = _todayWordUiState.asStateFlow()

     fun checkTodayWord() {
         Log.d("Cache", "checkTodayWord 호출 - wordList 크기: ${_todayWordUiState.value.wordList.size}")
         if(_todayWordUiState.value.wordList.isNotEmpty()) {
             Log.d("Cache", "캐시 HIT - 기존 데이터 사용")
             return
         }
         Log.d("Cache", "캐시 MISS - 새로 로드")
         loadStudyWord(10)
     }

    private fun loadStudyWord(count: Int) {
        viewModelScope.launch {
            loadStudyWordUseCase(count).collectLatest {roomResult->
                when (roomResult) {
                is AppResult.Success -> {
                    Log.d("StateUpdate", "업데이트 전 크기: ${_todayWordUiState.value.wordList.size}")
                    Log.d("StateUpdate", "받은 데이터 크기: ${roomResult.data.size}")

                _todayWordUiState.update {
                    it.copy(
                        wordList = roomResult.data, //실제 데이터 넣기
                        currentIndex = 0,
                        snackBarMessage = ""
                    )
                }
                    Log.d("StateUpdate", "업데이트 후 크기: ${_todayWordUiState.value.wordList.size}")
            }

                is AppResult.Failure -> {
                _todayWordUiState.update {
                    it.copy(
                        snackBarMessage = roomResult.exception.message.toString() ?: "Unknown error"
                    )
                }
            }

                is AppResult.Loading -> {}

                AppResult.NoConstructor -> {}
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
        } else{
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
        }else {
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

    fun toggleBookMark(id :String) {
        viewModelScope.launch {

        }
    }
}
