package com.hye.presentation.ui.component.list


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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hye.domain.model.roomdb.TargetWordWithAllInfoEntity
import com.hye.presentation.R

@Preview(apiLevel = 33, showBackground = true)
@Composable
fun BoomMarkLisPreview() {
    WordListItem(
        word = TargetWordWithAllInfoEntity(),
        onBookmarkClick = {},
        modifier = Modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordListItem(
    word: TargetWordWithAllInfoEntity,
    onBookmarkClick: () -> Unit = {},
    onItemClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    println("Rendering: ${word.documentId}, korean: ${word.korean}, isBookmarked: ${word.isBookmarked}")

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        )
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