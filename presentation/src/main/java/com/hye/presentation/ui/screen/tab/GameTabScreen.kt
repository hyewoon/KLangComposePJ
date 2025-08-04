package com.hye.presentation.ui.screen.tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.hye.presentation.R
import com.hye.presentation.ui.screen.model.GameViewModel
import com.hye.presentation.ui.screen.model.SharedViewModel
import com.hye.presentation.ui.theme.KLangComposePJTheme


@Composable
fun GameTabScreen(
    onNavigateToGameScreen: () -> Unit,
    onNavigateToTextToSpeechScreen: ()-> Unit,
    onNavigateToSearchScreen: ()->Unit,
    onNavigateToVocabularyScreen: ()->Unit,
    onNavigateToSpeechToTextScreen: ()->Unit,
    onNavigateToDrawScreen: () -> Unit,
    gameViewModel: GameViewModel,
    sharedViewModel: SharedViewModel,
) {
    KLangComposePJTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.character_k_smile),
                        contentDescription = "character_k_hello",
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 28.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ElevatedIconButton(
                        onClick =  {onNavigateToDrawScreen()},
                        icon = ImageVector.vectorResource(id = R.drawable.draw),
                        modifier = Modifier
                            .width(80.dp)
                            .height(120.dp)
                    )

                    ElevatedIconButton(
                        onClick = { onNavigateToTextToSpeechScreen()},
                        icon = ImageVector.vectorResource(id = R.drawable.text_to_speech),
                        modifier = Modifier
                            .width(80.dp)
                            .height(120.dp)
                    )
                    ElevatedIconButton(
                        onClick = { onNavigateToSpeechToTextScreen() },
                        icon = ImageVector.vectorResource(id = R.drawable.speech_to_text),
                        modifier = Modifier
                            .width(80.dp)
                            .height(120.dp)
                    )

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    ElevatedIconButton(
                        onClick = { onNavigateToSearchScreen() },
                        icon = ImageVector.vectorResource(id = R.drawable.search),
                        modifier = Modifier
                            .width(140.dp)
                            .height(80.dp)
                    )

                    ElevatedIconButton(
                        onClick = { onNavigateToVocabularyScreen() },
                        icon = ImageVector.vectorResource(id = R.drawable.vocabulary),
                        modifier = Modifier
                            .width(140.dp)
                            .height(80.dp)
                    )

                }


            }

        }
    }

}

@Composable
fun ElevatedIconButton(
    onClick: () -> Unit = {},
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(20.dp)

    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize()

        ) {
            Icon(
                imageVector = icon,
                contentDescription = "icon",
                tint = Color.Unspecified,
            )
        }

    }

}