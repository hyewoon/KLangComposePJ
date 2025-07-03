package com.hye.klangcomposepj.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hye.klangcomposepj.ui.theme.KLangComposePJTheme

@Preview(showBackground = true)
@Composable
fun DictionaryScreenPreview() {
    DictionaryScreen()
}

@Composable
fun DictionaryScreen(navController: NavController? = null) {
            Card(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxSize(),

                    ) {
                    Text(
                        text = "사과",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )

                    Text(
                        text = "apple",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "명사",
                        modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "모양이 둥글고 붉으며 새콤 하고 단맛이 나는 과일",
                        modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = Bold,
                        color = MaterialTheme.colorScheme.primary
                    )


                }
            }

        }



