package com.hye.presentation.ui.screen.home

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
import androidx.navigation.NavController
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.domain.model.roomdb.WordExampleInfoEntity
import com.hye.presentation.R
import com.hye.presentation.ui.component.home.todaystudy.TodayWordCard
import com.hye.presentation.ui.screen.model.HomeViewModel
import com.hye.presentation.ui.screen.model.SharedViewModel


@Composable
fun TodayStudyScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState,
) {
    val todayWordUiState by homeViewModel.todayWordUiState.collectAsStateWithLifecycle()

    LaunchedEffect(todayWordUiState.snackBarMessage) {
        if (todayWordUiState.snackBarMessage.isNotEmpty()) {
            snackBarHostState.showSnackbar(todayWordUiState.snackBarMessage)
            homeViewModel.clearSnackBarMessage()
        }
    }
    when {
        todayWordUiState.isLoading -> {}
        todayWordUiState.error.isNotEmpty() -> {}
        else -> {
            TodayStudyContent(
                navController = navController,
                wordList = todayWordUiState.wordList,
                currentIndex = todayWordUiState.currentIndex,
                currentWord = todayWordUiState.currentWord,
                currentWordExample = todayWordUiState.currentWordExample,
                hasNext = todayWordUiState.hasNext,
                hasPrevious = todayWordUiState.hasPrevious,
                onNextClick = { homeViewModel.moveToNext() },
                onPreviousClick = { homeViewModel.moveToPrevious() },
                )
        }
    }

}


@Composable
fun TodayStudyContent(
    navController: NavController,
    wordList: List<TargetWordWithAllInfoEntity>,
    currentIndex: Int,
    currentWord: TargetWordWithAllInfoEntity,
    currentWordExample: WordExampleInfoEntity,
    hasNext: Boolean,
    hasPrevious: Boolean,
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
        LinearProgressIndicatorBox(currentIndex + 1, wordList.size)
        Box(modifier = Modifier.weight(1f)) {
            TodayWordCard(
                navController = navController,
                wordList = wordList,
                currentIndex = currentIndex,
                currentWord = currentWord,
                hasNext = hasNext,
                hasPrevious = hasPrevious,
                onNextClick = onNextClick,
                onPreviousClick = onPreviousClick,
                onBookmarkClick = onBookmarkClick
            )
        }

        ExampleCard(currentWordExample)


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












