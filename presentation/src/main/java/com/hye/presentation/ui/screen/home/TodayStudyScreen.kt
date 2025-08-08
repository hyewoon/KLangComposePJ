package com.hye.presentation.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.domain.model.roomdb.WordExampleInfoEntity
import com.hye.presentation.R
import com.hye.presentation.model.TodayWordUiState
import com.hye.presentation.ui.component.home.todaystudy.TodayWordCard
import com.hye.presentation.ui.screen.model.HomeViewModel
import com.hye.presentation.ui.screen.model.SharedViewModel


@Composable
fun TodayStudyScreen(
    onNavigateToTodayStudyScreen: () -> Unit,
    onNavigateToListenScreen: () -> Unit,
    onNavigateToDictionaryScreen: () -> Unit,
    onNavigateToSpeechScreen: () -> Unit,
    onNavigateToWriteScreen: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState,
) {
    val todayWordUiState by homeViewModel.todayWordUiState.collectAsStateWithLifecycle()

            // 스낵바 처리
            LaunchedEffect(todayWordUiState.snackBarMessage) {
                if (todayWordUiState.snackBarMessage.isNotEmpty()) {
                    snackBarHostState.showSnackbar(todayWordUiState.snackBarMessage)
                    homeViewModel.clearSnackBarMessage()
                }
            }
            TodayStudyContent(
                onNavigateToListenScreen = onNavigateToListenScreen,
                onNavigateToDictionaryScreen = onNavigateToDictionaryScreen,
                onNavigateToSpeechScreen = onNavigateToSpeechScreen,
                onNavigateToWriteScreen = onNavigateToWriteScreen,
                todayWordUiState = todayWordUiState,
                onNextClick = { homeViewModel.moveToNext() },
                onPreviousClick = { homeViewModel.moveToPrevious() },
            )
        }


@Composable
fun TodayStudyContent(
    onNavigateToListenScreen: () -> Unit,
    onNavigateToDictionaryScreen: () -> Unit,
    onNavigateToSpeechScreen: () -> Unit,
    onNavigateToWriteScreen: () -> Unit,
    todayWordUiState : TodayWordUiState,
    onPreviousClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onBookmarkClick: () -> Unit = {},

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicatorBox(todayWordUiState.currentIndex + 1, todayWordUiState.wordList.size)
        Box(modifier = Modifier.weight(1f)) {
            TodayWordCard(
                onNavigateToListenScreen = onNavigateToListenScreen,
                onNavigateToDictionaryScreen = onNavigateToDictionaryScreen,
                onNavigateToSpeechScreen = onNavigateToSpeechScreen,
                onNavigateToWriteScreen = onNavigateToWriteScreen,
                todayWordUiState = todayWordUiState,
                onPreviousClick = onPreviousClick,
                onBookmarkClick = onBookmarkClick,
                onNextClick = onNextClick
            )
        }

        ExampleCard(todayWordUiState.currentWordExample)


    }
}

@Composable
fun LinearProgressIndicatorBox(current: Int, max: Int) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        LinearProgressIndicator(
            progress = { current.toFloat() / max.toFloat() },
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.secondary,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .wrapContentWidth()
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.paw),
                contentDescription = "paw",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Text(
                text = "$current/$max",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                maxLines = 1
            )
        }
    }
}

@Composable
fun ExampleCard(
    currentWordExample: WordExampleInfoEntity,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        bottom = 32.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = currentWordExample.example,
                    textAlign = TextAlign.Center,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Text(
                text = "예문 출처: 표준한국어대사전",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.surfaceTint,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
                    .align(Alignment.BottomEnd)
            )
        }

    }

}












