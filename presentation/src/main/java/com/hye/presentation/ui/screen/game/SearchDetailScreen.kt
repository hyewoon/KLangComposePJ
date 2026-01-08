package com.hye.presentation.ui.screen.game

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.domain.result.AppResult
import com.hye.presentation.R
import com.hye.presentation.ui.model.SearchViewModel
import com.hye.presentation.ui.model.SharedViewModel


@SuppressLint("RememberReturnType")
@Composable
fun SearchDetailScreen(
    targetCode: String,
    searchViewModel: SearchViewModel,
    sharedViewModel: SharedViewModel,
) {
    Log.d("SearchDetailScreen", "targetCode: $targetCode")

    LaunchedEffect(targetCode){
        searchViewModel.getDetailWordInfo(targetCode)
    }

    val searchWordUiState by searchViewModel.searchWordUiState.collectAsStateWithLifecycle()

    Column() {
        when {
            searchWordUiState.isLoading -> {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            searchWordUiState.word.isEmpty() -> {
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
            }

            else -> {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(
                        text = searchWordUiState.word,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, bottom = 16.dp),
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = Bold

                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Text(
                            text = "${searchWordUiState.pos} / ${searchWordUiState.wordGrade}",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = searchWordUiState.definition,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Start,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.background
                    )
                    Text(
                        text = "예문",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = Bold
                    )


                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Text(
                                text = searchWordUiState.example,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, bottom = 4.dp, top = 4.dp, end = 16.dp),
                                textAlign = TextAlign.Start,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize
                            )
                        }

                    }
                    /*    Text(
                            text = response.data.senses.flatMap { senses ->
                                senses.exampleInfo.filter { it.type == "문장" || it.type == "구" }
                                    .take(4).map { it.example }
                            }.joinToString("\n") { "• $it" },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp, end = 16.dp),
                            textAlign = TextAlign.Start,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )*/


                }

            }
        }

    }
}













