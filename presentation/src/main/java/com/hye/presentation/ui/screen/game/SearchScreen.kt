package com.hye.presentation.ui.screen.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hye.presentation.ui.component.searchbar.CustomSearchBar
import com.hye.presentation.ui.model.GameViewModel
import com.hye.presentation.ui.model.SharedViewModel

@Composable
fun SearchScreen(
    onNavigateToSearchScreen: ()-> Unit,
    sharedViewModel: SharedViewModel,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ){
        CustomSearchBar(modifier =Modifier)
    }
}