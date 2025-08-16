package com.hye.presentation.ui.model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.model.mlkit.HandWritingStroke
import com.hye.domain.repository.mlkit.MLKitRepository
import com.hye.domain.usecase.LoadStudyWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val mlkitRepository: MLKitRepository
) : ViewModel() {
    private val _recognizedText = MutableStateFlow("")
    val recognizedText = _recognizedText.asStateFlow()

    private val _isRecognizing = MutableStateFlow(false)
    val isRecognizing = _isRecognizing.asStateFlow()

    init{
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
            mlkitRepository.recognize(strokes)
        }
    }

}