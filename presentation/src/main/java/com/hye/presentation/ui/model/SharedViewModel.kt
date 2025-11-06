package com.hye.presentation.ui.model

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
    private val preferences: PreferencesDataStoreRepository,
) : ViewModel() {
    val totalWordCount = preferences.getTotalWordCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )


    fun checkLastStudyDate(){
        viewModelScope.launch{
            preferences.getLastStudyDate()
        }
    }

    fun saveLastStudyDate(date: String){
        viewModelScope.launch {
            preferences.saveLastStudyDate(date)
        }
    }

}

