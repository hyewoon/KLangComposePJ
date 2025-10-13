package com.hye.presentation.ui.component.dialog


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hye.presentation.R
import com.hye.presentation.ui.theme.KLangComposePJTheme


@Preview(apiLevel = 33, showBackground = true)
@Composable
fun CustomDialogPreview() {
    KLangComposePJTheme {
        CustomAlertDialog()
    }
}

@Composable
fun CustomConfirmationDialog(
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    dialogTitle: String = "",
    dialogText: String = "",
    icon: Painter = painterResource(id = R.drawable.paw_uncheck),
) {
    AlertDialog(
        icon = { Icon(painter = icon, contentDescription = "Example Icon") },
        title = {
            Text(
                text = dialogTitle,
                modifier = Modifier.fillMaxWidth(),
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = dialogText,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
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
                Text(
                    text = "확인",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surface,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    onDismissRequest: () -> Unit={},
    onConfirmation: () -> Unit={},
    dialogTitle: String ="",
    dialogText: String= "",
    confirmText: String ="",
    dismissText: String ="",
    icon: Painter? = null
) {
    AlertDialog(
        icon = if (icon != null) {
            { Icon(painter = icon, contentDescription = "Dialog Icon") }
        } else null,
        modifier = Modifier.padding(16.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        onDismissRequest =  onDismissRequest ,
        confirmButton = {
            TextButton(
                onClick =
                    onConfirmation
            ) {
                Text(text = confirmText)
            }
        },
        dismissButton = {
            TextButton(
                onClick =
                    onDismissRequest
            ) {
                Text(text =dismissText)
            }
        },
        title = {
            Text(
                text = dialogTitle,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = dialogText,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    )
}








