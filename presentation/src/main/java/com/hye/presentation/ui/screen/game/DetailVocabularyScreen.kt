package com.hye.presentation.ui.screen.game

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.presentation.R
import com.hye.presentation.ui.model.BookmarkViewModel
import com.hye.presentation.ui.model.SharedViewModel

@Composable
fun DetailVocabularyScreen(
    documentId: String,
    sharedViewModel: SharedViewModel,
    bookmarkViewModel: BookmarkViewModel,
) {
    val selectedWord by bookmarkViewModel.selectedWord.collectAsStateWithLifecycle()
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )

    ) {
        if (selectedWord == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("데이터를 불러올 수 없습니다. selectedWord is null")
            }
        } else {
            val word = selectedWord!!
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            )
            {
                //북마크
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(
                        onClick = {
                            bookmarkViewModel.toggleBookmark(
                                id = documentId,
                                currentBookmarkState = word.isBookmarked
                            )
                        }
                    ) {
                        Icon(
                            painter = painterResource(if (word.isBookmarked) R.drawable.star else R.drawable.star_uncheck),
                            contentDescription = "Bookmark",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(width = 40.dp, height = 40.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                ShowCurrentWord(word)
            }
        } ?: Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = "데이터를 불러 올 수 없습니다,"
            )

        }
    }
}

@Composable
fun ShowCurrentWord(word: TargetWordWithAllInfoEntity) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = word.korean,
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = word.english,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${word.pos}" + " / " + "${word.wordGrade}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(10.dp)),
            color = MaterialTheme.colorScheme.background
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "예문",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = word.exampleInfo.joinToString("\n\n") {
               " - ${it.example}"
            },
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start
        )
    }


}


@Composable
fun ShowDetailWord(documentId: String) {

}