package com.hye.klangcomposepj.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
fun SpeechScreenPreview() {
    SpeechScreen()
}
@Composable
fun SpeechScreen(navController: NavController? = null){
    Column(
        Modifier.padding(16.dp)
            .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally

    ){
        Text(text="가방",
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = Bold,
            textAlign = TextAlign.Center)

        Text(text="bag",
            modifier = Modifier.fillMaxWidth(),
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            textAlign = TextAlign.Center)

        Button(
            onClick = { },
            modifier = Modifier.width(200.dp)
        ){
            Text("녹음하기")
        }


        Card(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface)
        ){

        }




    }

}