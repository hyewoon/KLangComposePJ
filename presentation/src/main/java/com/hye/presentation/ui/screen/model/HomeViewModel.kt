package com.hye.presentation.ui.screen.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.result.RoomResult
import com.hye.domain.usecase.LoadStudyWordUseCase
import com.hye.presentation.model.TodayWordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadStudyWordUseCase: LoadStudyWordUseCase
) : ViewModel() {

    private val _todayWordUiState = MutableStateFlow(TodayWordUiState())
    val todayWordUiState: StateFlow<TodayWordUiState> = _todayWordUiState.asStateFlow()

    init {
        loadStudyWord(10)
        Log.d("HomeViewModel", "HomeViewModel initialized")
    }

    private fun loadStudyWord(count: Int) {
        viewModelScope.launch {
            //stateflow의 초기값 지정
            _todayWordUiState.value = _todayWordUiState.value.copy(isLoading = true)

            when (val roomResult = loadStudyWordUseCase(count)) {
                is RoomResult.Success -> {
                    _todayWordUiState.update {
                        it.copy(
                            wordList = roomResult.data,
                            isLoading = false,
                            error = ""
                        )
                    }
                }

                is RoomResult.RoomDBError -> {
                    _todayWordUiState.update {
                        it.copy(
                            isLoading = false,
                            error = roomResult.exception.message ?: "Unknown error"
                        )
                    }
                }

                is RoomResult.Loading -> {
                    _todayWordUiState.update { it.copy(isLoading = true) }
                }

                else -> {}

            }

        }
    }

    fun moveToNext() {
        _todayWordUiState.update {
            if (it.hasNext) {
                it.copy(currentIndex = it.currentIndex + 1)
            } else {
                it.copy(snackBarMessage = "마지막 단어 입니다.")
            }
        }
    }

    fun moveToPrevious() {
        _todayWordUiState.update {
            if (it.hasPrevious) {
                it.copy(currentIndex = it.currentIndex - 1)
            } else {
                it.copy(snackBarMessage = "첫번째 단어 입니다.")
            }
        }
    }

    fun clearSnackBarMessage() {
        _todayWordUiState.update { it.copy(snackBarMessage = "") }

    }

}