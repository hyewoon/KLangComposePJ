package com.hye.presentation.ui.model

import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hye.presentation.manager.STTManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class STTViewModel @Inject constructor(
    private val stt: STTManager
): ViewModel() {

    private val _recognizedText = mutableStateOf("")
    val recognizedText: State<String> = _recognizedText

    private val _isListening = mutableStateOf(false)
    val isListening: State<Boolean> = _isListening

    fun createSpeechIntent(): Intent {
        _recognizedText.value = ""
        _isListening.value = true
        return stt.createSTTIntent()
    }

    fun handleResult(data: Intent?) {
        _isListening.value = false
        _recognizedText.value = stt.getTextFromResult(data)
    }

}