package com.hye.presentation.ui.component.dialog


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hye.presentation.ui.theme.KLangComposePJTheme


@Preview(apiLevel = 33,showBackground = true)
@Composable
fun CustomDialogPreview(){
 KLangComposePJTheme{
  CustomDialog()
 }
}

@Composable
fun CustomDialog(
    onDismissRequest: ()-> Unit={},
    onConfirmation: ()-> Unit={},
    dialogTitle: String = "",
    dialogText: String = "",
){
     AlertDialog(
         title = {
             Text(text = "문자를 인식 할 수 없어요.",
                 modifier = Modifier.fillMaxWidth(),
                 fontSize = MaterialTheme.typography.titleMedium.fontSize,
                 color = MaterialTheme.colorScheme.onSurface,
                 textAlign = androidx.compose.ui.text.style.TextAlign.Center)
         },
         text = {
             Text(text = "다시 입력해 주세요",
                 modifier =Modifier.fillMaxWidth(),
                 color = MaterialTheme.colorScheme.onSurface,
                 textAlign = androidx.compose.ui.text.style.TextAlign.Center)
         },
         onDismissRequest = {
             onDismissRequest()
         },
         confirmButton = {
           Button(
                 onClick = {
                 onConfirmation()
             })
             {
                 Text(text = "확인",
                     modifier = Modifier.fillMaxWidth(),
                     color = MaterialTheme.colorScheme.surface,
                     fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                     textAlign = androidx.compose.ui.text.style.TextAlign.Center)
             }
         },
         containerColor = MaterialTheme.colorScheme.surface,
             )

     }





