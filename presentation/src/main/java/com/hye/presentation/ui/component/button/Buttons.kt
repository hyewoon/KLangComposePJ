package com.hye.presentation.ui.component.button

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hye.presentation.ui.theme.KLangComposePJTheme


@Preview(showBackground = true)
@Composable
fun CustomButtonSmallPreview() {
     KLangComposePJTheme {
         CustomButtonSmall("입력",
             onClick = {},
             modifier = Modifier)
     }
}

@Composable
fun CustomButtonSmall(
    label: String="",
    onClick:()-> Unit= {},
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier.width(100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        )
    ){
        Text(label)
    }
}

enum class InputMode {
    KEYBOARD, HANDWRITING
}

@Composable
fun SegmentedButton(){

}