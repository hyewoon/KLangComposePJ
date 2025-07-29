package com.hye.presentation.ui.component.home.todaystudy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.presentation.R
import com.hye.presentation.nav_graph.ScreenRoutDef


@Composable
fun TodayWordCard(
    navController: NavController,
    wordList: List<TargetWordWithAllInfoEntity>,
    currentIndex: Int,
    currentWord: TargetWordWithAllInfoEntity,
    hasNext: Boolean,
    hasPrevious: Boolean,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onBookmarkClick: () -> Unit = {},
) {
    val isMarked = true
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            //북마크
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = if (isMarked) {
                        painterResource(id = R.drawable.star)
                    } else {
                        painterResource(id = R.drawable.star_uncheck)
                    },
                    contentDescription = "bookmark",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(width = 40.dp, height = 40.dp)
                        .padding(8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.paw_uncheck),
                    contentDescription = "bookmark",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(width = 40.dp, height = 40.dp)
                        .padding(8.dp)
                )

            }
            ShowCurrentWord(
                currentWord,
                hasPrevious,
                hasNext,
                onPreviousClick,
                onNextClick
            )

            //순서
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${currentIndex+1}/${wordList.size}" ,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize)
            }
        }
        //디바이더
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(10.dp)),
            color = MaterialTheme.colorScheme.background
        )
        SelectButtons(navController)


    }

}

@Composable
fun ShowCurrentWord(
    currentWord: TargetWordWithAllInfoEntity,
    hasPrevious: Boolean,
    hasNext: Boolean,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onPreviousClick) {
            Icon(
                painter = painterResource(id = R.drawable.prev),
                contentDescription = "prev",
                tint = Color.Unspecified
            )
        }

        Text(
            text = currentWord.korean,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        IconButton(onClick = onNextClick) {
            Icon(
                painter = painterResource(id = R.drawable.next),
                contentDescription = "next",
                tint = Color.Unspecified
            )

        }
    }
    Column() {
        Text(
            text = currentWord.english,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }


}

@Composable
fun SelectButtons(navController: NavController) {
    val isMarked = false
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        IconButton(
            onClick = {
                navController.navigate(ScreenRoutDef.TodayStudyFlow.WriteScreen.routeName)
            },
            modifier = Modifier.size(70.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.write_btn),
                contentDescription = "sound",
                tint = Color.Unspecified,
                modifier = Modifier.size(60.dp)
            )
        }
        IconButton(
            onClick = { navController.navigate(ScreenRoutDef.TodayStudyFlow.ListenScreen.routeName) },
            modifier = Modifier.size(70.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.listen_btn),
                contentDescription = "sound",
                tint = Color.Unspecified,
                modifier = Modifier.size(60.dp)
            )

        }
        IconButton(
            onClick = { navController.navigate(ScreenRoutDef.TodayStudyFlow.SpeechScreen.routeName) },
            modifier = Modifier.size(70.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.speech_btn),
                contentDescription = "sound",
                tint = Color.Unspecified,
                modifier = Modifier.size(60.dp)
            )

        }
        IconButton(
            onClick = {
                navController.navigate(ScreenRoutDef.TodayStudyFlow.DictionaryScreen.routeName)
            },
            modifier = Modifier.size(70.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.dictionary_btn),
                contentDescription = "sound",
                tint = Color.Unspecified,
                modifier = Modifier.size(60.dp)
            )
        }


    }

}