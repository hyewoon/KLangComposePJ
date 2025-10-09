package com.hye.presentation.ui.component.searchbar


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.hye.presentation.R


@Composable
fun CustomSearchBar(
    query: String = "",
    onQueryChange: (String) -> Unit = {}
) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    "검색어를 입력하세요",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.surfaceTint
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "search_icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )

            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "cancel",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            },
            shape = RoundedCornerShape(28.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,  // 추가
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface  // 추가


                ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    //검색 로직
                }
            )
        )
}