package com.hye.presentation.manager

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TTSManager @Inject constructor() {
    private lateinit var tts: TextToSpeech
    private var isReady = false  // TTS 초기화 완료 상태를 추적


    fun initTTS(context: Context, onInitComplete: () -> Unit = {}) {
        if (::tts.isInitialized) {
            if (isReady) onInitComplete()
            return
        }
        tts = TextToSpeech(context) { status ->
            isReady = if (status == TextToSpeech.SUCCESS) {
                    val result = tts.setLanguage(Locale.KOREA)
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                        result == TextToSpeech.LANG_NOT_SUPPORTED
                    ) {
                        false
                    } else {
                        tts.setPitch(1.0f)
                        tts.setSpeechRate(0.8f)
                        onInitComplete()
                        true
                    }

            } else {
                false
            }
        }
    }

    fun readText(text: String) {
        if (!::tts.isInitialized  || !isReady) {
            return
        } else {
            tts.speak(text, TextToSpeech.QUEUE_ADD, null, null)
        }
    }

    fun pause(){
        if(::tts.isInitialized && isReady)
        tts.stop()
    }

    fun release() {
        if(::tts.isInitialized && isReady)
            tts.stop()
            tts.shutdown()

            isReady = false
        }
}