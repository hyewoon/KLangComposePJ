package com.hye.presentation.ui.screen.game


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.presentation.ui.component.searchbar.CustomTTSInputBar
import com.hye.presentation.ui.model.SharedViewModel
import com.hye.presentation.ui.model.TTSViewModel

@Composable
fun TextToSpeechScreen(
    onNavigateToTextToSpeechScreen: ()-> Unit,
    sharedViewModel: SharedViewModel,
    ttsViewModel: TTSViewModel = hiltViewModel()
) {
    val text by ttsViewModel.text.collectAsStateWithLifecycle()
    val isTTSReady by ttsViewModel.isTTSReady.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "텍스트를 입력하고, 마이크 버튼을 누르세요.",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTTSInputBar(
            query = text,
            onQueryChange =  {ttsViewModel.updateText(it)},
            onIconClick = {
                ttsViewModel.speakCurrentText()
            }
        )
    }
}



