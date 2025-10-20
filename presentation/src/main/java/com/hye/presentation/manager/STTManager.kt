package com.hye.presentation.manager


import android.content.Intent
import android.speech.RecognizerIntent
import javax.inject.Inject

class STTManager @Inject constructor() {

    fun createSTTIntent(): Intent {
        return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")
            putExtra(RecognizerIntent.EXTRA_PROMPT, "단어를 말해주세요")
        }
    }

  fun getTextFromResult(data: Intent?): String{
      val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
      return results?.get(0) ?: ""
  }

}