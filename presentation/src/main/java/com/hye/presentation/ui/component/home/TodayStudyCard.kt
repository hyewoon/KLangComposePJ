package com.hye.presentation.ui.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hye.presentation.R
import com.hye.presentation.nav_graph.ScreenRoutDef


@Preview(showBackground = true)
@Composable
fun TodayStudyCardPreview() {
    //TodayStudyCard()
}


@Composable
fun TodayStudyCard(onNavigateToTodayStudy:()-> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .size(width = 320.dp, height = 250.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            start = 24.dp,
                            end = 16.dp,
                            top = 16.dp,
                            bottom = 16.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicatorBox(4, 10)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "어휘 학습",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "하루 목표 10개",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            start = 16.dp,
                            end = 24.dp,
                            top = 16.dp,
                            bottom = 16.dp
                        ),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.character_k),
                        contentDescription = "character_k",
                        modifier = Modifier.size(width = 120.dp, height = 140.dp),
                        contentScale = ContentScale.Fit
                    )
                }

            }
            Button(
                onClick = {
                 onNavigateToTodayStudy()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
            {
                Text(
                    text = "오늘의 학습",
                    style = MaterialTheme.typography.bodyMedium
                )
            }


        }
    }


}


@Composable
fun CircularProgressIndicatorBox(progress: Int, max: Int) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(95.dp),
            progress = { progress / max.toFloat() },
            strokeWidth = 10.dp,
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = progress.toString(),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alignByBaseline()
            )
            Text(
                text = "/",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                modifier = Modifier.alignByBaseline()
            )
            Text(
                text = max.toString(),
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                modifier = Modifier.alignByBaseline()
            )
        }
    }

}


