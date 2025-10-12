package com.hye.presentation.ui.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hye.domain.repository.roomdb.StudyRepository
import com.hye.domain.result.AppResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val roomRepository: StudyRepository,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }


    val bookmarkWords = roomRepository.getBookmarkedWords(true)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AppResult.Loading
        )

    init {
        // 🔍 디버깅용 로그
        viewModelScope.launch {
            bookmarkWords.collect { result ->
                when (result) {
                    is AppResult.Loading -> {
                        Log.d("BookmarkViewModel", "⏳ Loading...")
                    }
                    is AppResult.Success -> {
                        Log.d("BookmarkViewModel", "✅ Success: ${result.data.size} items")
                        result.data.forEach { word ->
                            Log.d("BookmarkViewModel", "  - ${word.korean} / ${word.english}")
                        }
                    }
                    is AppResult.Failure -> {
                        Log.e("BookmarkViewModel", "❌ Failure: ${result.exception}")
                    }
                    else -> {
                        Log.d("BookmarkViewModel", "🤷 Other state")
                    }
                }
            }
        }
    }


    fun toggleBookmark(id: String, currentBookmarkState: Boolean) {
            viewModelScope.launch {
                val newBookmarkState = !currentBookmarkState
                val timeStamp = if (newBookmarkState) System.currentTimeMillis() else 0L

                Log.d("BookmarkViewModel", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
                Log.d("BookmarkViewModel", "🔖 Toggle Bookmark")
                Log.d("BookmarkViewModel", "   ID: $id")
                Log.d("BookmarkViewModel", "   Current State: $currentBookmarkState")
                Log.d("BookmarkViewModel", "   New State: $newBookmarkState")
                Log.d("BookmarkViewModel", "   Timestamp: $timeStamp")
                Log.d("BookmarkViewModel", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
               val result =  roomRepository.updateBookmarkStatus(
                    documentId = id,
                    isBookmarked = newBookmarkState,
                    bookmarkedTimeStamp = timeStamp
                )
                when(result){
                    is AppResult.Success -> {
                        Log.d("BookmarkViewModel", "북마크 업데이트 성공")
                    }

                    is AppResult.Failure -> {
                        Log.e("BookmarkViewModel", "   ❌ FAILURE: ${result.exception}")
                    }
                    else -> {
                        Log.w("BookmarkViewModel", "   ⚠️ Unexpected result: $result")
                    }
                }
                Log.d("BookmarkViewModel", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
            }

    }

}