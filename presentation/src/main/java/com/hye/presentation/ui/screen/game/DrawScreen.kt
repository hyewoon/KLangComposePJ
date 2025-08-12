package com.hye.presentation.ui.screen.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.hye.presentation.ui.component.common.CustomButtonSmall
import com.hye.presentation.ui.component.common.DrawingCustomView
import com.hye.presentation.ui.model.GameViewModel
import com.hye.presentation.ui.model.SharedViewModel

@Composable
fun DrawScreen(
    onNavigateToDrawScreen: () -> Unit,
    gameViewModel: GameViewModel,
    sharedViewModel: SharedViewModel,
) {
    val recongizedText by gameViewModel.recognizedText.collectAsState()
    val isRecognizing by gameViewModel.isRecognizing.collectAsState()

    var customView: DrawingCustomView? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()){
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        DrawingCustomView(
                            context,
                            attrs = null,
                        ).apply{
                            setWatermarkText(recongizedText)

                            customView = this

                        }
                    }, update = {customView->
                        customView.isEnabled = !isRecognizing

                    }

                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    CustomButtonSmall(
                        label="입력",
                        onClick = {
                            val strokes = customView?.getCurrentStrokes()
                            if (strokes != null) {
                              //  gameViewModel.sendStrokes(strokes)
                            }
                        }
                    )

                    CustomButtonSmall(
                        label="지우기",
                        onClick = {
                            customView?.clearCanvas()
                        }

                    )
                }

            }

        }
    }
}