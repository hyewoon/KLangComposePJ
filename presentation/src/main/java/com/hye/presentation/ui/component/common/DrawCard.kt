package com.hye.presentation.ui.component.common

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.hye.presentation.ui.component.button.CustomButtonSmall
import com.hye.presentation.ui.theme.KLangComposePJTheme

@Preview(apiLevel = 33, showBackground = true)
@Composable
fun DrawScreenPreview() {
    KLangComposePJTheme {
        DrawCard()
    }
}

@Composable
fun DrawCard(recognizedText:String = "") {

    //객체 참조
    var customView: DrawingCustomView? by remember { mutableStateOf(null) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    DrawingCustomView(
                        context,
                        attrs = null,
                    ).apply {
                        setWatermarkText(recognizedText)
                        customView = this

                    }
                }, update = { customView ->


                }

            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomButtonSmall(
                    label = "입력",
                    onClick = {

                    }
                )

                CustomButtonSmall(
                    label = "지우기",
                    onClick = {
                        Log.d("DEBUG", "버튼 클릭됨!!!") // 이게 로그에 나오나요?
                       customView?.clearCanvas()
                    }

                )
            }

        }

    }

}