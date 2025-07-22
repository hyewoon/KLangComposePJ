package com.hye.presentation.ui.screen.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hye.presentation.R
import com.hye.presentation.ui.screen.model.HomeViewModel
import com.hye.presentation.ui.screen.model.SharedViewModel


@Composable
fun ListenScreen(navController: NavController,
                 homeViewModel: HomeViewModel,
                 sharedViewModel: SharedViewModel
){
    Column(
        modifier = Modifier.padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
            Text(text="가방",
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                textAlign = TextAlign.Center)

            Text(text="bag",
                modifier = Modifier.fillMaxWidth(),
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                textAlign = TextAlign.Center)

            IconButton(
                onClick = {  },
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)
            ){
                Icon(painter = painterResource(id = R.drawable.sound),
                    contentDescription = "sound",
                    tint = Color.Unspecified)
            }







    }

}