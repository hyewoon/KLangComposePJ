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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hye.presentation.R

/*
@Preview(apiLevel = 33,showBackground = true)
@Composable
fun TodayWordCardPreview(){
    TodayWordCard(
        onNavigateToListenScreen = TODO(),
        onNavigateToDictionaryScreen = TODO(),
        onNavigateToSpeechScreen = TODO(),
        onNavigateToWriteScreen = TODO(),
        todayWordUiState = TODO(),
        onNextClick = TODO(),
        onPreviousClick = TODO(),
    )
}
*/


@Composable
fun TodayWordCard(
    onNavigateToListenScreen: () -> Unit,
    onNavigateToDictionaryScreen: () -> Unit,
    onNavigateToSpeechScreen: () -> Unit,
    onNavigateToWriteScreen: () -> Unit,
    documentId: String,
    isBookmarked: Boolean,
    korean: String,
    english: String,
    currentIndex: Int,
    totalWords: Int,
    onNextClick: () -> Unit = {},
    onPreviousClick: () -> Unit = {},
    onBookmarkToggle : (String, Boolean)-> Unit,
) {

    //documentId와 isBookmarked가 같으면 같은 람다 재사용
    val handleBookmarkToggle = remember(documentId,isBookmarked) {
        {
            onBookmarkToggle(documentId, isBookmarked)
        }
    }

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
            key(isBookmarked) {
                BookmarkSection(
                    isBookmarked = isBookmarked,
                    onBookmarkToggle = handleBookmarkToggle
                )
            }
            key(korean, english) {
                ShowCurrentWord(
                    korean = korean,
                    english = english,
                    onPreviousClick = onPreviousClick,
                    onNextClick = onNextClick,
                )
            }

            key(currentIndex, totalWords){
                WordIndexIndicator(
                    currentIndex = currentIndex,
                    totalWords = totalWords
                )
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
        SelectButtons(
            onNavigateToListenScreen,
            onNavigateToDictionaryScreen,
            onNavigateToSpeechScreen,
            onNavigateToWriteScreen,
        )


    }

}

@Composable
fun ShowCurrentWord(
    korean: String,
    english: String,
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
            text = korean,
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
            text = english,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }


}

@Composable
fun SelectButtons(
    onNavigateToListenScreen: () -> Unit,
    onNavigateToDictionaryScreen: () -> Unit,
    onNavigateToSpeechScreen: () -> Unit,
    onNavigateToWriteScreen: () -> Unit,
) {
    val isMarked = false
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        IconButton(
            onClick = { onNavigateToWriteScreen() },
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
            onClick = { onNavigateToListenScreen() },
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
            onClick = { onNavigateToSpeechScreen() },
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
            onClick = { onNavigateToDictionaryScreen() },
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

@Composable
fun BookmarkSection(
    isBookmarked : Boolean,
    onBookmarkToggle : ()-> Unit,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBookmarkToggle) {
            Icon(
                painter = painterResource(id = if (isBookmarked) R.drawable.star else R.drawable.star_uncheck),
                contentDescription = "Bookmark",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(width = 40.dp, height = 40.dp)
                    .padding(8.dp)
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.paw_uncheck),
            contentDescription = "bookmark",
            tint = Color.Unspecified,
            modifier = Modifier
                .size(width = 40.dp, height = 40.dp)
                .padding(8.dp)
        )
    }
}

@Composable
fun WordIndexIndicator(
    currentIndex: Int,
    totalWords: Int,
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${currentIndex + 1}/${totalWords}",
            fontSize = MaterialTheme.typography.bodySmall.fontSize
        )
    }

}
