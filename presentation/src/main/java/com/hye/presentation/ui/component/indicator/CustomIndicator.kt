package com.hye.presentation.ui.component.indicator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hye.presentation.ui.theme.KLangComposePJTheme


@Preview(apiLevel = 33, showBackground = true)
@Composable
fun CustomIndeterminateCircularIndicatorPreview() {
    KLangComposePJTheme {
        CustomIndeterminateCircularIndicator()
    }
}

@Composable
fun CustomIndeterminateCircularIndicator() {
    Box(modifier = Modifier.fillMaxSize(),
        Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.width(100.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
    }
}
