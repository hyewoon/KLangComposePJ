package com.hye.presentation.ui.model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.presentation.manager.TTSManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TTSViewModel @Inject constructor(
    private val ttsManager: TTSManager
) : ViewModel() {

    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text.asStateFlow()

    private val _isTTSReady = MutableStateFlow(false)
    val isTTSReady: StateFlow<Boolean> = _isTTSReady.asStateFlow()

    fun updateText(text: String) {
        _text.value = text

    }

    fun speakCurrentText(){
        val currentText = _text.value
        if (currentText.isNotEmpty()) {
            speakText(currentText)
        }

    }

    private fun speakText(text: String) {
        viewModelScope.launch {
            ttsManager.readText(text)
        }
    }

    fun pauseTTS() {
        ttsManager.pause()
    }

    override fun onCleared() {
        super.onCleared()
        ttsManager.release()
    }
}