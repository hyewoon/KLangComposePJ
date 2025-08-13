package com.hye.presentation.ui.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hye.presentation.ui.theme.KLangComposePJTheme

@Preview(apiLevel = 33, showBackground = true)
@Composable
fun CustomCardSmallPreview() {
    KLangComposePJTheme {
        CustomResultCardSmall("사과")
    }
}

@Composable
fun CustomResultCardSmall(text: String = "") {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(16.dp),
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

        }
    }
}
