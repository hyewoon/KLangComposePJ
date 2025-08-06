package com.hye.presentation.ui.screen.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.result.RoomResult
import com.hye.domain.usecase.LoadStudyWordUseCase
import com.hye.presentation.model.TodayWordUiState
import com.hye.presentation.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadStudyWordUseCase: LoadStudyWordUseCase,
) : ViewModel() {

    private val _todayWordUiState = MutableStateFlow(TodayWordUiState())
    val todayWordUiState: StateFlow<TodayWordUiState> = _todayWordUiState.asStateFlow()

    init {
        loadStudyWord(10)
        Log.d("HomeViewModel", "HomeViewModel initialized")
    }

    private fun loadStudyWord(count: Int) {
        viewModelScope.launch {

            when (val roomResult = loadStudyWordUseCase(count)) {
                is RoomResult.Success -> {
                    _todayWordUiState.update {
                        it.copy(
                            wordList = roomResult.data, //실제 데이터 넣기
                            currentIndex = 0,
                            snackBarMessage = "",
                            bookMarkedIndices = emptySet()
                        )
                    }
                }

                is RoomResult.RoomDBError -> {
                    _todayWordUiState.update {
                        it.copy(
                            snackBarMessage = roomResult.exception.message ?: "Unknown error"
                        )
                    }
                }

                is RoomResult.Loading -> {}

                RoomResult.NoConstructor -> {}
            }
        }

        /*   when (val roomResult = loadStudyWordUseCase(count)) {
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

           }*/

    }


    fun moveToNext() {
        val currentState = _todayWordUiState.value
        if (currentState.hasNext) {
            _todayWordUiState.update {
                it.copy(currentIndex = it.currentIndex + 1)
            }
        }
    }

    fun moveToPrevious() {
        val currentState = _todayWordUiState.value
        if (currentState.hasPrevious) {
            _todayWordUiState.update {
                it.copy(currentIndex = it.currentIndex - 1)
            }
        }
    }


    fun clearSnackBarMessage() {
        _todayWordUiState.update {
            it.copy(snackBarMessage = "")
        }
    }
}
