package com.hye.presentation.ui.screen.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.repository.datastore.PreferencesDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
private val preferencesManager : PreferencesDataStoreRepository
): ViewModel() {

    private val _targetWordCount = preferencesManager.targetWordCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000), 0)

    val targetWordCount = _targetWordCount

    private val _currentWordCount = preferencesManager.currentWordCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000), 0)

    val currentWordCount = _currentWordCount

    suspend fun saveTargetWordCount(count: Int) {
        viewModelScope.launch {
            preferencesManager.writePreference("target_word_count", count)
        }
    }
    suspend fun saveCurrentWordCount(count: Int) {
        viewModelScope.launch {
            preferencesManager.writePreference("current_word_count", count)
        }
    }


}

