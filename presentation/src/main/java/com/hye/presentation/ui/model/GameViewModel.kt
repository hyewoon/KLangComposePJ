package com.hye.presentation.ui.model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.model.mlkit.HandWritingAnalysis
import com.hye.domain.model.mlkit.HandWritingStroke
import com.hye.domain.repository.mlkit.MLKitRepository
import com.hye.domain.result.AppResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val mlkitRepository: MLKitRepository
) : ViewModel() {
    private var _recognizedText = MutableStateFlow("")
    val recognizedText = _recognizedText.asStateFlow()

    private var _isRecognizing = MutableStateFlow(false)
    val isRecognizing = _isRecognizing.asStateFlow()

    private var _recognitionResult = MutableStateFlow<AppResult<HandWritingAnalysis>>(AppResult.NoConstructor)
    val recognitionResult = _recognitionResult.asStateFlow()

    init {
        viewModelScope.launch {
            mlkitRepository.initialize()
        }
    }

    fun startRecognizing() {
        _isRecognizing.value = true
    }

    fun completeRecognition(result: String) {
        _recognizedText.value = result
        _isRecognizing.value = false

    }

    fun sendStrokes(strokes: List<HandWritingStroke>) {
        viewModelScope.launch {
            mlkitRepository.recognize(strokes).collectLatest { mlkitResult ->
                when (mlkitResult) {
                    is AppResult.Success -> {
                        startRecognizing()

                        _recognitionResult.value = AppResult.Success(mlkitResult.data)
                    }
                    is AppResult.Failure -> {
                        _isRecognizing.value = false
                        _recognitionResult.value = AppResult.Failure(mlkitResult.exception)
                    }

                    AppResult.Loading -> {
                        _isRecognizing.value = true
                    }
                    AppResult.NoConstructor -> {
                        _isRecognizing.value = false
                    }
                }
            }
        }

    }
    fun clearRecognitionResult(){
        _recognitionResult.value = AppResult.NoConstructor

    }
}