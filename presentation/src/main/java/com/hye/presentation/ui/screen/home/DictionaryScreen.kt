package com.hye.presentation.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hye.presentation.ui.screen.model.HomeViewModel
import com.hye.presentation.ui.screen.model.SharedViewModel


@Composable
fun DictionaryScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    sharedViewModel: SharedViewModel,
    snackBarHostState: SnackbarHostState
) {
            Card(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
            ) {
                Column(
                    modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 32.dp).fillMaxSize(),

                    ) {
                    Text(
                        text = "사과",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = Bold
                    )

                    Text(
                        text = "apple",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "명사",
                        modifier = Modifier.fillMaxWidth().padding(start=16.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize

                    )
                    Text(
                        text = "모양이 둥글고 붉으며 새콤 하고 단맛이 나는 과일",
                        modifier = Modifier.fillMaxWidth().padding(start=16.dp),
                        textAlign = TextAlign.Start,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        color = MaterialTheme.colorScheme.background)

                    Text(text="모양이 둥글고 붉으며 새콤 하고 단맛이 나는 과일",
                        modifier = Modifier.fillMaxSize(),
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        textAlign = TextAlign.Center)

                }
            }

        }



