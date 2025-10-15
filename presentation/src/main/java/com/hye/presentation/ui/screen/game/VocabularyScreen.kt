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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.domain.result.AppResult
import com.hye.presentation.R
import com.hye.presentation.ui.component.dialog.CustomAlertDialog
import com.hye.presentation.ui.component.indicator.CustomIndeterminateCircularIndicator
import com.hye.presentation.ui.component.list.WordListItem
import com.hye.presentation.ui.component.searchbar.CustomSearchBar
import com.hye.presentation.ui.model.BookmarkViewModel
import com.hye.presentation.ui.model.SharedViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocabularyScreen(
    onNavigationToDetailScreen: (String) -> Unit,
    sharedViewModel: SharedViewModel,
    bookmarkViewModel: BookmarkViewModel,

    ) {
    val searchQuery by bookmarkViewModel.searchQuery.collectAsStateWithLifecycle()
    val bookmarkWords by bookmarkViewModel.bookmarkWords.collectAsStateWithLifecycle()


    // 다이얼로그
    var showDialog by remember { mutableStateOf(false) }
    //삭제할 데이터 (id, korea)
    var wordToDelete by remember { mutableStateOf<Pair<String, String>?>(null) }
    Log.d("VocabularyScreen", "showDialog: $showDialog, wordToDelete: $wordToDelete")


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
        )
        when (val result = bookmarkWords) {
            is AppResult.Loading -> {
                Log.d("BookmarkViewModel", "⏳ Loading...")
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CustomIndeterminateCircularIndicator()
                }

            }

            is AppResult.Success -> {
                if (result.data.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("북마크된 단어가 없습니다")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        items(
                            items = result.data,
                            key = { it.documentId }

                        ) { words ->
                            WordListItem(

                                word = words,
                                modifier = Modifier,
                                onBookmarkClick = {
                                    /*
                                    * 클릭이 감지 되면,
                                    * */
                                    wordToDelete = Pair(words.documentId, words.korean)
                                    showDialog = true
                                },
                                /*
                                * 디테일페이지로 이동
                                * */
                                onItemClick = {
                                    Log.d(
                                        "VocabularyScreen",
                                        "Clicked word: ${words.documentId}, ${words.korean}"
                                    )
                                    bookmarkViewModel.selectWord(words)
                                    Log.d("VocabularyScreen", "After selectWord")
                                    onNavigationToDetailScreen(words.documentId)
                                },

                                )
                        }
                    }

                }

            }

            is AppResult.Failure -> {}
            is AppResult.NoConstructor -> {}
        }
    }

    if (showDialog && wordToDelete != null) {
        CustomAlertDialog(
            icon = painterResource(id = R.drawable.paw_uncheck),
            onDismissRequest = {
                showDialog = false
                wordToDelete = null
            },
            onConfirmation = {
                //북마크 해제
                bookmarkViewModel.toggleBookmark(
                    id = wordToDelete!!.first,
                    currentBookmarkState = true
                )
                showDialog = false
                wordToDelete = null
            },
            dialogTitle = "\"${wordToDelete!!.second}\"\n 북마크에서 삭제할까요?",
            dialogText = "북마크 해제 하면 내 단어장에서\n 제거 됩니다.",
            confirmText = "삭제",
            dismissText = "취소"
        )
    }
}

