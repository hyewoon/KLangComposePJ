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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Preview(showBackground = true)
@Composable
fun ListenScreenPreview(){
    ListenScreen()
}

@Composable
fun ListenScreen(navController: NavController? = null){
    Column(
        modifier = Modifier.padding(16.dp)
            .height(300.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
            Text(text="가방",
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                textAlign = TextAlign.Center)

            Text(text="bag",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)

            Card(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface)
            ){

            }








    }

}