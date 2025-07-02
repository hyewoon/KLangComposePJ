package com.hye.klangcomposepj.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hye.klangcomposepj.R
import com.hye.klangcomposepj.nav_graph.ScreenRoutDef
import com.hye.klangcomposepj.ui.theme.KLangComposePJTheme


@Composable
fun TodayStudyScreen(navController: NavController) {
    KLangComposePJTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicatorBox(4, 10, modifier = Modifier.weight(0.1f))
                TodayWordCard(modifier = Modifier.weight(0.5f), navController)
                Card(
                    modifier = Modifier
                        .weight(0.4f)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "모자를 벗어서 가방 속에 넣었다.",
                            textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            modifier = Modifier.padding(16.dp)
                        )
                        Text(
                            text = "예문 출처: 표준한국어대사전",
                            fontSize = 8.sp,
                            color = MaterialTheme.colorScheme.surfaceTint,
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)
                        )
                    }
                }


            }
        }
    }

}


@Composable
fun LinearProgressIndicatorBox(current: Int, max: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        LinearProgressIndicator(
            progress = { current.toFloat() / max.toFloat() },
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.secondary,
            strokeCap = androidx.compose.ui.graphics.StrokeCap.Round,
            modifier = Modifier
                .weight(0.7f)
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Row(
            modifier = Modifier.weight(0.3f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.paw),
                contentDescription = "paw",
                modifier = Modifier.weight(0.7f),
                tint = Color.Unspecified
            )
            Text(
                text = current.toString(),
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
            Text(
                text = "/",
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
            Text(
                text = max.toString(),
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        }
    }
}

@Composable
fun TodayWordCard(modifier: Modifier = Modifier, navController: NavController) {

     val isMarked =true
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            //북마크
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = if(isMarked){
                       painterResource(id = R.drawable.star)
                    }else{
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
            ShowCurrentWord()
            Text(
                text = "go",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            //순서
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "1",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                )
                Text(
                    text = "/",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                )
                Text(
                    text = "10",
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
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
        SelectButtons(navController)


    }

}

@Composable
fun ShowCurrentWord() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.prev),
                contentDescription = "prev",
                tint = Color.Unspecified
            )
        }

        Text(
            text = "가다",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )

        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.next),
                contentDescription = "next",
                tint = Color.Unspecified
            )

        }
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
                modifier = Modifier.size(70.dp)
            )
        }
        IconButton(onClick = { navController.navigate(ScreenRoutDef.TodayStudyFlow.ListenScreen.routeName) },
            modifier = Modifier.size(90.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.listen_btn),
                contentDescription = "sound",
                tint = Color.Unspecified,
                modifier = Modifier.size(70.dp)
            )

        }
        IconButton(
            onClick = { navController.navigate(ScreenRoutDef.TodayStudyFlow.SpeechScreen.routeName) },
            modifier = Modifier.size(90.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.speech_btn),
                contentDescription = "sound",
                tint = Color.Unspecified,
                modifier = Modifier.size(70.dp)
            )

        }
        IconButton(
            onClick = {
                navController.navigate("ScreenRoutDef.TodayStudyFlow.DictionaryScreen.routeName")
            },
            modifier = Modifier.size(90.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.dictionary_btn),
                contentDescription = "sound",
                tint = Color.Unspecified,
                modifier = Modifier.size(70.dp)
            )
        }


    }

}








