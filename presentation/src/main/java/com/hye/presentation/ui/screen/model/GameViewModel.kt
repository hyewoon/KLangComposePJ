package com.hye.presentation.ui.screen.model


import androidx.lifecycle.ViewModel
import com.hye.domain.usecase.LoadStudyWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    val useCase: LoadStudyWordUseCase?
) : ViewModel() {
    private val _recognizedText = MutableStateFlow("")
   val recognizedText = _recognizedText.asStateFlow()

    private val _isRecognizing = MutableStateFlow(false)
    val isRecognizing = _isRecognizing.asStateFlow()

    fun startRecognizing() {
       _isRecognizing.value = true
    }

    fun completeRecognition(result: String){
        _recognizedText.value = result
        _isRecognizing.value = false

    }

}