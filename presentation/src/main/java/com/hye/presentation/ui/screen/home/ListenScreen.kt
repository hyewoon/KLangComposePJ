package com.hye.presentation.ui.screen.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.presentation.R
import com.hye.presentation.ui.model.SharedViewModel
import com.hye.presentation.ui.model.TTSViewModel


@Composable
fun ListenScreen(
    korean: String,
    english: String,
    onNavigateToListenScreen: (String, String) -> Unit,
    sharedViewModel: SharedViewModel,
    ttsViewModel: TTSViewModel= hiltViewModel(),
    snackBarHostState: SnackbarHostState
){


    val text by ttsViewModel.text.collectAsStateWithLifecycle()
    val isTTSReady by ttsViewModel.isTTSReady.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
            Text(text= korean,
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                textAlign = TextAlign.Center)

            Text(text= english,
                modifier = Modifier.fillMaxWidth(),
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                textAlign = TextAlign.Center)

            IconButton(
                onClick = {
                    ttsViewModel.updateText(korean)
                    ttsViewModel.speakCurrentText()
                },
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)
            ){
                Icon(painter = painterResource(id = R.drawable.sound),
                    contentDescription = "sound",
                    tint = Color.Unspecified)
            }







    }

}