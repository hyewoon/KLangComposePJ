package com.hye.presentation.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.model.api.WordEntity
import com.hye.domain.repository.api.DetailWordRepository
import com.hye.domain.repository.api.WordRepository
import com.hye.domain.result.AppResult
import com.hye.presentation.model.SearchWordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val _searchWordUiState =
        MutableStateFlow(SearchWordUiState(isLoading = true))
    val searchWordUiState: StateFlow<SearchWordUiState> =
        _searchWordUiState.asStateFlow()

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
            _searchWordUiState.value = SearchWordUiState(isLoading = true)
            val result = detailWordRepository.getDetailWordInfo(targetCode)

            _searchWordUiState.value = when (result) {
                is AppResult.Success -> {
                    SearchWordUiState(
                        word = result.data.word,
                        pos = result.data.pos,
                        wordGrade = result.data.wordGrade,
                        definition = result.data.senses.mapIndexed { index, sense ->
                            "${index + 1}. ${sense.definition}"
                        }.joinToString("\n"),
                        example = result.data.senses.flatMap { senses ->
                            senses.exampleInfo.map {
                                it.example
                            }
                        }.joinToString("\n") { "• $it" }
                    )

                }

                is AppResult.Failure -> {
                    SearchWordUiState(
                        isLoading = false,
                    )
                }

                else -> {
                    SearchWordUiState(
                        isLoading = false,
                    )
                }
            }

        }
    }
}


