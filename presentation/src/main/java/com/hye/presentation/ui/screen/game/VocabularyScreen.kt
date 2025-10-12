package com.hye.presentation.ui.screen.game

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.domain.result.AppResult
import com.hye.presentation.ui.component.indicator.CustomIndeterminateCircularIndicator
import com.hye.presentation.ui.component.list.WordListItem
import com.hye.presentation.ui.component.searchbar.CustomSearchBar
import com.hye.presentation.ui.model.BookmarkViewModel
import com.hye.presentation.ui.model.SharedViewModel


@Preview(apiLevel = 33, showBackground = true)
@Composable
fun VocabularyScreenPreview() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocabularyScreen(
    onNavigateToVocabularyScreen: () -> Unit,
    sharedViewModel: SharedViewModel,
    bookmarkViewModel: BookmarkViewModel = hiltViewModel()

) {
    val bookmarkWords by bookmarkViewModel.bookmarkWords.collectAsStateWithLifecycle()
    val searchQuery by bookmarkViewModel.searchQuery.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        CustomSearchBar(
            query = searchQuery,
            onQueryChange = bookmarkViewModel::onSearchQueryChange,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        when (val result = bookmarkWords) {
            is AppResult.Loading -> {
                Log.d("BookmarkViewModel", "⏳ Loading...")
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    CustomIndeterminateCircularIndicator()
                }

            }
            is AppResult.Success -> {
                Log.d("BookmarkViewModel", "✅ Success: ${result.data.size} items")
                if(result.data.isEmpty()){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("북마크된 단어가 없습니다")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                            .weight(1f)
                    ) {
                        items(
                            items = result.data,
                            key = { it.documentId }

                        ) { words ->
                            Log.d("VocabularyScreen", "Rendering item: ${words.documentId}, isBookmarked: ${words.isBookmarked}")
                            WordListItem(
                                word = words,
                                modifier = Modifier,
                                onBookmarkClick = {
                                    bookmarkViewModel.toggleBookmark(
                                        id = words.documentId,
                                        currentBookmarkState = words.isBookmarked
                                    )
                                }
                            )
                        }
                    }

                }

                }
            is AppResult.Failure -> {}
            is AppResult.NoConstructor -> {}
        }
    }
}

