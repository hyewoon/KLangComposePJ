package com.hye.presentation.ui.screen.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hye.presentation.R
import com.hye.presentation.model.DrawingPath
import com.hye.presentation.ui.component.common.HandWritingCanvasComponent
import com.hye.presentation.ui.screen.model.GameViewModel
import com.hye.presentation.ui.screen.model.SharedViewModel

@Composable
fun DrawScreen(
    navController: NavController,
    gameViewModel: GameViewModel,
    sharedViewModel: SharedViewModel,
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

    DrawingCard(completedPaths = completedPaths, currentPath = currentPath,
        onPathsChanged = { newPaths -> completedPaths = newPaths },
        onCurrentPathChanged = { newPath -> currentPath = newPath })
}

@Composable
fun DrawingCard(
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
        }
        Text(
            text = "원하는 단어를 써보세요",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
                        modifier = Modifier.size(40.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = "delete",
                            tint = Color.Unspecified,
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        HandWritingCanvasComponent(
                            completedPaths = completedPaths,
                            currentPath = currentPath,
                            onPathsChanged = onPathsChanged,
                            onCurrentPathChanged = onCurrentPathChanged,
                        )

                    }
                }

            }
        }

    }
}