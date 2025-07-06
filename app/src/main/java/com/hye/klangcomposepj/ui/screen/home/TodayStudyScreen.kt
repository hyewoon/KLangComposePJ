package com.hye.klangcomposepj.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.hye.klangcomposepj.ui.component.home.todaystudy.TodayWordCard
import com.hye.klangcomposepj.ui.theme.KLangComposePJTheme


@Composable
fun TodayStudyScreen(navController: NavController) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicatorBox(4, 10)
        TodayWordCard(navController)
        ExampleCard()

    }
}

@Composable
fun LinearProgressIndicatorBox(current: Int, max: Int) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        LinearProgressIndicator(
            progress = { current.toFloat() / max.toFloat() },
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.secondary,
            strokeCap = androidx.compose.ui.graphics.StrokeCap.Round,
            modifier = Modifier
                .wrapContentWidth()
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.paw),
                contentDescription = "paw",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Text(
                text = "$current/$max",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                maxLines = 1
            )
        }
    }
}

@Composable
fun ExampleCard() {
    Card(
        modifier = Modifier
            .wrapContentWidth(),
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
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
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












