package com.hye.presentation.ui.component.list


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hye.domain.model.api.WordEntity
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.presentation.R

/*
@Preview(apiLevel = 33, showBackground = true)
@Composable
fun BoomMarkLisPreview() {
    BookmarkedWordListItem(
        word = TargetWordWithAllInfoEntity(),
        onBookmarkClick = {},
        modifier = Modifier
    )
}*/

@Preview(apiLevel = 33, showBackground = true)
@Composable
fun SearchWordListItemPreview() {
    SearchWordListItem(
        word = WordEntity(),
        onItemClick = {},
        modifier = Modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkedWordListItem(
    word: TargetWordWithAllInfoEntity,
    onBookmarkClick: () -> Unit = {},
    onItemClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
            .clickable { onItemClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        ),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = word.korean,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = word.english,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                onBookmarkClick()
            }
            ) {
                Icon(
                    painter = painterResource(
                        id = if (word.isBookmarked) R.drawable.star else R.drawable.star_uncheck
                    ),
                    contentDescription = "star",
                    tint = Color.Unspecified,
                )


            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchWordListItem(
    word: WordEntity,
    onItemClick: () -> Unit = {},
    modifier: Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onItemClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 10.dp, top = 16.dp)
        ) {
            Text(
                text = word.word,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alignByBaseline(),

                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            val translations =
                word.sense.take(5).joinToString { it.transWord }
            Text(
                text = translations,
                modifier = Modifier.alignByBaseline(),
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 10.dp)
        ) {
            Text(
                text = word.pos,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = word.wordGrade ?: "등급 없음",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        {
            /*  val definition = word.sense.firstOrNull()?.definition?.removeSurrounding("[", "]") ?: ""
              if (definition.isNotEmpty()) {
                  val meaning = definition.split(",").map { it.trim() }

                  meaning.forEachIndexed { index, meaning ->
                      if (meaning.isNotEmpty()) {
                          Text(
                              text = "${index + 1}. $meaning",
                              fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                          )

                      }
                  }
              }*/

            val definition = word.sense.take(3).joinToString("\n") {
                "${it.senseOrder}. ${it.definition}"
            }
            Text(
                text = definition,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                modifier = Modifier.padding(bottom=8.dp)
            )
        }
    }
}




