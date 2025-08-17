package com.hye.presentation.ui.screen.game

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.hye.presentation.ui.component.button.CustomButtonSmall
import com.hye.presentation.ui.component.card.CustomResultCardSmall
import com.hye.presentation.ui.component.common.DrawingCustomView
import com.hye.presentation.ui.model.GameViewModel
import com.hye.presentation.ui.model.SharedViewModel

@Composable
fun DrawScreen(
    onNavigateToDrawScreen: () -> Unit,
    gameViewModel: GameViewModel,
    sharedViewModel: SharedViewModel,
) {
    val recognizedText by gameViewModel.recognizedText.collectAsState()
    val isRecognizing by gameViewModel.isRecognizing.collectAsState()
    val recognitionResult by gameViewModel.recognitionResult.collectAsState()

    //객체 참조
    var customView: DrawingCustomView? by remember { mutableStateOf(null) }
    //애니메니션 적용
    var fold by remember { mutableStateOf(false) }


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
            Box(
                modifier = Modifier
                    .animateContentSize()
                    .height(if(fold) 250.dp else 400.dp)
                    .fillMaxWidth()
            ) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        DrawingCustomView(
                            context,
                            attrs = null,
                        ).apply {
                           // setWatermarkText(recognizedText)

                            customView = this

                        }
                    }, update = { customView ->
                        customView.isEnabled = !isRecognizing

                    }

                )

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomButtonSmall(
                        label = if(fold) "초기화" else "입력",
                        onClick = {
                            if(!fold) {
                                val strokes = customView?.getCurrentStrokes()
                                if (!strokes.isNullOrEmpty()) {
                                    gameViewModel.sendStrokes(strokes)
                                    fold = true
                                }
                            }else {
                                fold = false
                                customView?.clearCanvas()
                            }
                        }
                    )

                    CustomButtonSmall(
                        label = "지우기",
                        onClick = {
                            if(!fold) {
                                customView?.clearCanvas()
                            }
                        }

                    )
                }

            }

        }
        if(fold){
            CustomResultCardSmall(recognizedText)
        }

    }
}