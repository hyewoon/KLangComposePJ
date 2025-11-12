package com.hye.presentation.ui.screen.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.domain.result.AppResult
import com.hye.presentation.ui.component.dialog.CustomConfirmationDialog
import com.hye.presentation.ui.component.indicator.CustomIndeterminateCircularIndicator
import com.hye.presentation.ui.component.list.SearchWordListItem
import com.hye.presentation.ui.component.searchbar.CustomSearchBar
import com.hye.presentation.ui.model.SearchViewModel
import com.hye.presentation.ui.model.SharedViewModel


@Composable
fun SearchScreen(
    onNavigateToSearchScreen: () -> Unit,
    onNavigateToSearchDetailScreen: (String) -> Unit,
    sharedViewModel: SharedViewModel,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    //val searchQuery by searchViewModel.searchQuery.collectAsStateWithLifecycle()

    var searchQuery by remember { mutableStateOf("") }

    val wordList by searchViewModel.wordList.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogText by remember { mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        CustomSearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onIconClick = {
                if (searchQuery.isNotEmpty()) {
                    focusManager.clearFocus()
                    searchViewModel.searchWord(searchQuery)
                }
            }
        )

        when (val response = wordList) {
            is AppResult.Loading -> {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CustomIndeterminateCircularIndicator()
                }
            }

            is AppResult.Success -> {
                if (response.data.isEmpty()) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("검색 결과가 없습니다.",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        items(
                            items = response.data,
                            key = { it.targetCode }
                        ) { word ->
                            SearchWordListItem(
                                word = word,
                                modifier = Modifier,
                                /*
                                 * 디테일페이지 이동
                                 * */
                                onItemClick = {
                                    onNavigateToSearchDetailScreen(word.targetCode)
                                }

                            )

                        }
                    }
                }
            }

            is AppResult.Failure -> {
                LaunchedEffect(response.exception){

                when {
                    response.exception.contains("네트워크") -> {
                        showDialog = true
                        dialogTitle = "네트워크에 연결할 수 없습니다."
                        dialogText = "네트워크를 연결해 주세요."


                    }

                    response.exception.contains("서버") -> {
                        showDialog = true
                        dialogTitle = "서버에 연결할 수 없습니다."
                        dialogText = "잠시 후 다시 시도해 주세요."
                    }

                    else -> {}
                }

                }

            }

            is AppResult.NoConstructor -> {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "검색어를 입력해주세요.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

        }
    }
    if (showDialog) {
        CustomConfirmationDialog(
            onDismissRequest = { showDialog = false },
            onConfirmation = { showDialog = false },
            dialogTitle = dialogTitle,
            dialogText = dialogText
        )

    }
}





