package com.hye.presentation.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.repository.roomdb.StudyRepository
import com.hye.domain.result.AppResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val roomRepository: StudyRepository,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedWord = MutableStateFlow<TargetWordWithAllInfoEntity?>(null)
    val selectedWord : StateFlow<TargetWordWithAllInfoEntity?> = _selectedWord.asStateFlow()


    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun selectWord(word: TargetWordWithAllInfoEntity){
        _selectedWord.value = word
    }

    //전체 데이터 -필터링 전
    private val _allBookmarkWords = roomRepository.getBookmarkedWords(true)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AppResult.Loading
        )


    val bookmarkWords = combine(
        _allBookmarkWords,
        _searchQuery
    ) { wordsResult, query ->
        when (wordsResult) {
            is AppResult.Success -> {
                if (query.isEmpty()) {
                    // 검색어가 없으면 전체 표시
                    wordsResult
                } else {
                    // 검색어로 필터링 : 영어, 한국어 양방향
                    val filteredWords = wordsResult.data.filter { word ->
                        word.korean.contains(query, ignoreCase = true) ||
                                word.english.contains(query, ignoreCase = true)
                    }
                    AppResult.Success(filteredWords)
                }
            }

            is AppResult.Loading -> wordsResult
            is AppResult.Failure -> wordsResult
            is AppResult.NoConstructor -> wordsResult

        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AppResult.Loading
    )


    fun toggleBookmark(id: String, currentBookmarkState: Boolean) {
        viewModelScope.launch {
            val newBookmarkState = !currentBookmarkState
            val timeStamp = if (newBookmarkState) System.currentTimeMillis() else 0L

            val result = roomRepository.updateBookmarkStatus(
                documentId = id,
                isBookmarked = newBookmarkState,
                bookmarkedTimeStamp = timeStamp
            )
        }
    }
}