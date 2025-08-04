package com.hye.presentation.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.presentation.R
import com.hye.presentation.model.DrawingPath
import com.hye.presentation.model.TodayWord
import com.hye.presentation.model.UIState
import com.hye.presentation.ui.component.common.HandWritingCanvasComponent
import com.hye.presentation.ui.screen.model.HomeViewModel
import com.hye.presentation.ui.screen.model.SharedViewModel

@Preview(showBackground = true)
@Composable
fun WriteScreenPreview() {
     WriteScreen()
}

@Composable
fun WriteScreen(
    onNavigateToWriteScreen : ()-> Unit={},
    homeViewModel: HomeViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
) {

    var completedPaths by remember { mutableStateOf(listOf<DrawingPath>()) }
    var currentPath by remember {
        mutableStateOf(
            DrawingPath(
                Path(),
                Color.Black,
                5f
            )
        )
    }


    val todayWordUiState by homeViewModel.todayWordUiState.collectAsStateWithLifecycle()

    when(todayWordUiState){
        is UIState.Loading -> {}
        is UIState.Error -> {}
        is UIState.Success -> {
            val todayWord = (todayWordUiState as UIState.Success<TodayWord>).data
            val currentWord = todayWord.currentWord

            DrawingCard(
                korean = currentWord.korean,
                english = currentWord.english,
                completedPaths = completedPaths,
                currentPath = currentPath,
                onPathsChanged = { newPaths -> completedPaths = newPaths },
                onCurrentPathChanged = { newPath -> currentPath = newPath }
            )


        }

    }
}

@Composable
fun DrawingCard(
    korean: String,
    english: String,
    completedPaths: List<DrawingPath>,
    currentPath: DrawingPath,
    onPathsChanged: (List<DrawingPath>) -> Unit = {},
    onCurrentPathChanged: (DrawingPath) -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = korean,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = english,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                textAlign = TextAlign.Center
            )

        }
        Text(
            text = "단어를 따라 쓰세요",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                HandWritingCanvasComponent(
                    completedPaths = completedPaths,
                    currentPath = currentPath,
                    onPathsChanged = onPathsChanged,
                    onCurrentPathChanged = onCurrentPathChanged,
                    watermarkText = korean,
                )

                IconButton(
                    onClick = {
                        onPathsChanged(emptyList())
                        onCurrentPathChanged(
                            DrawingPath(
                                Path(),
                                currentPath.color,
                                5f
                            )
                        )
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(40.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "delete",
                        tint = Color.Unspecified,
                    )
                }
            }

        }
    }

}