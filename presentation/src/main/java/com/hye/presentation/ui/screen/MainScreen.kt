package com.hye.presentation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hye.presentation.R
import com.hye.presentation.nav_graph.MainNavHost
import com.hye.presentation.nav_graph.ScreenRoutDef
import com.hye.presentation.ui.component.bottom_navigationbar.MainBottomNavigationBar
import com.hye.presentation.ui.component.top_app_bar.MainTopAppBar
import com.hye.presentation.ui.model.BookmarkViewModel
import com.hye.presentation.ui.model.HomeViewModel
import com.hye.presentation.ui.model.SharedViewModel
import com.hye.presentation.ui.model.TTSViewModel


@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedGetBackStackEntry",
    "WrongNavigateRouteType"
)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    //네비게이션 설정
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val sharedViewModel: SharedViewModel= hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()
    val ttsViewModel: TTSViewModel = hiltViewModel()
    //snackbar 설정
    val snackBarHostState = remember { SnackbarHostState() }

    val totalWordCount by sharedViewModel.totalWordCount.collectAsStateWithLifecycle()
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            MainTopAppBar(currentDestination = currentDestination,
                onBackClick = { navController.popBackStack() },
                totalWordCount = totalWordCount)

        },
        bottomBar = {
            MainBottomNavigationBar(
                navController = navController,
                navBackStackEntry = navBackStackEntry,
                currentDestination = currentDestination
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.background)
        ) {
            MainNavHost(
                navController = navController,
                sharedViewModel = sharedViewModel,
                homeViewModel = homeViewModel,
                bookmarkViewModel = bookmarkViewModel,
                ttsViewModel = ttsViewModel,
            )
        }
    }
}


@Composable
fun ShowBackButton(currentRoute: NavDestination?): Boolean {
    val topLevelRoutes = listOf(
        ScreenRoutDef.TopLevel.HomeTab::class.qualifiedName,
        ScreenRoutDef.TopLevel.GameTab::class.qualifiedName,
        ScreenRoutDef.TopLevel.MyPageTab::class.qualifiedName
    )
    return currentRoute?.route in topLevelRoutes
}

@Composable
fun AppBarItems(
    totalWordCount: Int,modifier: Modifier = Modifier) {
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