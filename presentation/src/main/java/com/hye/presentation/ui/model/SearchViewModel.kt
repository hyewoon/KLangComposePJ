package com.hye.presentation.ui.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.model.api.DetailWordEntity
import com.hye.domain.model.api.WordEntity
import com.hye.domain.repository.api.DetailWordRepository
import com.hye.domain.repository.api.WordRepository
import com.hye.domain.result.AppResult
import com.hye.presentation.ui.component.dialog.CustomConfirmationDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val wordRepository: WordRepository,
    private val detailWordRepository: DetailWordRepository,
) : ViewModel() {


    //단어 결과값
    private val _wordList = MutableStateFlow<AppResult<List<WordEntity>>>(AppResult.NoConstructor)
    val wordList = _wordList.asStateFlow()

    //단어 상세 정보
    private val _detailWordInfo =
        MutableStateFlow<AppResult<List<DetailWordEntity>>>(AppResult.NoConstructor)
    val detailWordInfo = _detailWordInfo.asStateFlow()

    fun searchWord(word: String) {
        viewModelScope.launch {
            _wordList.value = AppResult.Loading

           _wordList.value = wordRepository.getWordInfo(word)

        }
    }

 /*   fun formatWordInfo(wordEntity: WordEntity): String {
        return wordEntity.sense.take(3).joinToString("\n\n") {
            "${it.senseOrder}. ${it.transWord}\n ${it.definition}"

        }
    }*/

    fun getDetailWordInfo(targetCode: String) {
        viewModelScope.launch {
            val detailResponse = detailWordRepository.getDetailWordInfo(targetCode)
            detailResponse?.let {
                _detailWordInfo.value = it
            }
        }
    }
}

