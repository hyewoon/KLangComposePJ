package com.hye.presentation.ui.component.top_app_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.hye.presentation.R
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


@Composable
fun AppBarItems(
    totalWordCount: Int, modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PointItem(R.drawable.paw, totalWordCount)
        Spacer(modifier = Modifier.width(20.dp))
        PointItem(R.drawable.point, 200)
    }

}

@Composable
fun PointItem(
    iconResId: Int,
    point: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = "point_icon",
            tint = Color.Unspecified,
            modifier = modifier
        )
        Spacer(modifier.width(16.dp))
        Text(
            text = point.toString(),
            modifier = modifier,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )
    }
}


