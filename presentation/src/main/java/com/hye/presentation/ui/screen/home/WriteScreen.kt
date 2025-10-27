package com.hye.presentation.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.presentation.ui.component.common.DrawCard
import com.hye.presentation.ui.model.SharedViewModel

@Composable
fun WriteScreen(
    korean: String,
    english: String,
    onNavigateToWriteScreen: (String, String) -> Unit,
    sharedViewModel: SharedViewModel,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
) {


    DrawingCard(
        korean = korean,
        english = english,
    )
}

@Composable
fun DrawingCard(
    korean: String,
    english: String,
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = korean,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
        Text(
            text = english,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        DrawCard(
            mode = com.hye.presentation.ui.component.common.DrawingMode.TRACING,
            recognizedText = korean,
            onDrawingCompleted = {}
        )
    }

}