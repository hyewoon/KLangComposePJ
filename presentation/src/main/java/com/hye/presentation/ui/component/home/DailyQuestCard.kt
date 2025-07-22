package com.hye.presentation.ui.component.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.hye.presentation.R

@Preview(showBackground = true)
@Composable
fun DailyQuestCard() {
    Column(
    ) {
        Text(
            text = "일일 퀘스트",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.paw_uncheck),
                            contentDescription = "paw_uncheck",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(width = 60.dp, height = 60.dp)
                        )
                        Text(
                            text = "오늘의 학습",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.point_uncheck),
                            contentDescription = "point_uncheck",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(width = 60.dp, height = 60.dp)
                        )

                        Text(
                            text = "게임하기",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                    }


                }
            }
        }

    }

}