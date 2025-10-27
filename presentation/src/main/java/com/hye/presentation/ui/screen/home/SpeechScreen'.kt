package com.hye.presentation.ui.screen.home

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hye.presentation.ui.model.STTViewModel
import com.hye.presentation.ui.model.SharedViewModel


@Composable
fun SpeechScreen(
    korean: String,
    english: String,
    onNavigateToSpeechScreen :(String,String)->Unit,
    sharedViewModel: SharedViewModel,
    snackBarHostState: SnackbarHostState,
    sttViewModel : STTViewModel = hiltViewModel()
){
    val recognizedText by sttViewModel.recognizedText
    val isListening by sttViewModel.isListening

    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            sttViewModel.handleResult(result.data)
        }
    }
    Column(
        Modifier.padding(16.dp)
            .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally

    ){
        Text(text= korean,
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = Bold,
            textAlign = TextAlign.Center)

        Text(text= english,
            modifier = Modifier.fillMaxWidth(),
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            textAlign = TextAlign.Center)

        Button(
            onClick = {
                speechRecognizerLauncher.launch(sttViewModel.createSpeechIntent())
            },
            modifier = Modifier.width(200.dp)
        ){
            Text("녹음하기")
        }







    }

}