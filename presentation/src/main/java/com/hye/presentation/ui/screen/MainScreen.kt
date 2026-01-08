package com.hye.presentation.ui.screen

import android.annotation.SuppressLint
import android.util.Log
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
import com.hye.presentation.ui.model.SearchViewModel
import com.hye.presentation.ui.model.SettingViewModel
import com.hye.presentation.ui.model.SharedViewModel
import com.hye.presentation.ui.model.TTSViewModel


@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedGetBackStackEntry",
    "WrongNavigateRouteType", "SuspiciousIndentation"
)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    //네비게이션 설정
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val sharedViewModel: SharedViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val searchViewModel: SearchViewModel = hiltViewModel()
    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()
    val ttsViewModel: TTSViewModel = hiltViewModel()
    val settingViewModel: SettingViewModel = hiltViewModel()
    //snackbar 설정
    val snackBarHostState = remember { SnackbarHostState() }

    val totalWordCount by sharedViewModel.totalWordCount.collectAsStateWithLifecycle()
    Log.d("MainScreen", "totalWordCount: $totalWordCount")
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            MainTopAppBar(
                currentDestination = currentDestination,
                onBackClick = { navController.popBackStack() },
                totalWordCount = totalWordCount
            )

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
                searchViewModel = searchViewModel,
                settingViewModel = settingViewModel,
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

