package com.hye.presentation.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.repository.datastore.PreferencesDataStoreRepository
import com.hye.presentation.model.TodayWordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val preferences: PreferencesDataStoreRepository,

    ) : ViewModel() {
    private val _totalWordCount = MutableStateFlow(0)
    val totalWordCount = _totalWordCount.asStateFlow()

    init {
        //앱 시작하면 누적 학습 단어 가져오기
        viewModelScope.launch {
            preferences.getTotalWordCount().collectLatest { count ->
                _totalWordCount.update { count }
            }
        }

    }


}

