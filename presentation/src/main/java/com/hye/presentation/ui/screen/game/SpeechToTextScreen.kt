package com.hye.presentation.ui.screen.game

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.hye.presentation.R
import com.hye.presentation.ui.model.STTViewModel
import com.hye.presentation.ui.model.SharedViewModel

@Composable
fun SpeechToTextScreen(
    onNavigateToSpeechToTextScreen: ()-> Unit,
    sharedViewModel: SharedViewModel,
    sttViewModel : STTViewModel = hiltViewModel()
) {

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
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
            Text(
                text = recognizedText.ifEmpty { "음석 인식을 하려면 마이크 버튼을 누르세요." },
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = if(recognizedText.isNotEmpty()) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))
            IconButton(
                onClick = {
                    speechRecognizerLauncher.launch(sttViewModel.createSpeechIntent())
                },
                enabled = !isListening,
                modifier = Modifier.size(100.dp)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.mic),
                    contentDescription = "microphone",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(80.dp)
                )
            }
        }


}