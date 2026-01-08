package com.hye.presentation.ui.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.model.AppLanguage
import com.hye.domain.repository.datastore.PreferencesDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val preferences: PreferencesDataStoreRepository,
): ViewModel() {
  val currentLanguage : StateFlow<AppLanguage> = preferences.getLanguage()
      .stateIn(
          scope = viewModelScope,
          started = SharingStarted.WhileSubscribed(5000),
          initialValue = AppLanguage.ENGLISH
      )

  private val _languageChangeEvent = MutableSharedFlow<AppLanguage>(
      extraBufferCapacity = 1,
  )
    val languageChangeEvent = _languageChangeEvent.asSharedFlow()

       fun updateLanguage(language: AppLanguage) {
            Log.d("SettingViewModel", "언어 저장: ${language.code}")
            viewModelScope.launch {
                //dataStore저장
                preferences.saveLanguage(language)
                //mainActivity 이벤트 발행
                _languageChangeEvent.emit(language)
                Log.d("SettingViewModel", "저장 완료")  // 👈 로그
            }
        }
    }
