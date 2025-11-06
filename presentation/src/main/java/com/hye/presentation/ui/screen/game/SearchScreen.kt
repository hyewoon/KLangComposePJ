package com.hye.presentation.ui.screen.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.domain.result.AppResult
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
    val searchQuery by searchViewModel.searchQuery.collectAsStateWithLifecycle()
    val wordList by searchViewModel.wordList.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        CustomSearchBar(
            query = searchQuery,
            onQueryChange = searchViewModel::onSearchQueryChange,
            onIconClick = {
                if(searchQuery.isNotEmpty()) {
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
                        Text("검색 결과가 없습니다.")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                         items(
                             items = response.data,
                             key = {it.targetCode}
                         ){word->
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

            is AppResult.Failure -> {}
            is AppResult.NoConstructor ->{}
        }
    }
}


