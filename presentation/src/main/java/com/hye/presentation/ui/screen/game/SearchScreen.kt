package com.hye.presentation.ui.screen.game

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.domain.result.AppResult
import com.hye.presentation.R
import com.hye.presentation.ui.component.dialog.CustomConfirmationDialog
import com.hye.presentation.ui.component.indicator.CustomIndeterminateCircularIndicator
import com.hye.presentation.ui.component.list.SearchWordListItem
import com.hye.presentation.ui.component.searchbar.CustomSearchBar
import com.hye.presentation.ui.model.SearchViewModel
import com.hye.presentation.ui.model.SharedViewModel
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun SearchScreen(
    onNavigateToSearchScreen: () -> Unit,
    onNavigateToSearchDetailScreen: (String) -> Unit,
    sharedViewModel: SharedViewModel,
    searchViewModel: SearchViewModel,
    onDetailNavigate: (String) -> Unit,
) {
    //val searchQuery by searchViewModel.searchQuery.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }

    val wordList by searchViewModel.wordList.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle: Int by remember { mutableIntStateOf(0) }
    var dialogText: Int by remember { mutableIntStateOf(0) }
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
                        Text(
                            stringResource(R.string.search_response_message),
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
                                    Log.d("SearchScreen", "targetCode: ${word.targetCode}")
                                }

                            )

                        }
                    }
                }
            }

            is AppResult.Failure -> {
                val exception = response.exception
                LaunchedEffect(response.exception) {

                    when {
                        exception is UnknownHostException ||
                                exception is ConnectException ||
                                exception is SocketException -> {
                            showDialog = true
                            dialogTitle = R.string.error_message_network
                            dialogText = R.string.network_connection_required


                        }

                        response.exception is HttpException -> {
                            showDialog = true
                            dialogTitle = R.string.error_message_server
                            dialogText = R.string.retry_again
                        }

                        response.exception is SocketTimeoutException -> {
                            showDialog = true
                            dialogTitle = R.string.connection_timed_out
                            dialogText = R.string.retry_again
                        }
                        else -> {
                            showDialog = true
                            dialogTitle = R.string.error_occurred

                        }
                    }

                }

            }

            is AppResult.NoConstructor -> {
            }

        }
    }
    if (showDialog) {
        CustomConfirmationDialog(
            onDismissRequest = { showDialog = false },
            onConfirmation = { showDialog = false },
            dialogTitle = stringResource(dialogTitle),
            dialogText = stringResource(dialogText)
        )

    }
}





