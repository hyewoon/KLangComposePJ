package com.hye.presentation.ui.component.top_app_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import com.hye.presentation.ui.screen.AppBarItems
import com.hye.presentation.ui.screen.ShowBackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(currentDestination: NavDestination?, onBackClick: () -> Unit, totalWordCount: Int) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
        ),
        title = {
            AppBarItems(totalWordCount )
        },
        navigationIcon = {
            if (!(ShowBackButton(currentDestination))) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
                }
            }
        }
    )
}
