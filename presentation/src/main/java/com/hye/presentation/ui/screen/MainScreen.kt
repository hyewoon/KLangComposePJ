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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hye.presentation.R
import com.hye.presentation.nav_graph.BottomNavigationItem
import com.hye.presentation.nav_graph.ScreenRoutDef
import com.hye.presentation.nav_graph.nav_graph_extended.addGameGraph
import com.hye.presentation.nav_graph.nav_graph_extended.addHomeGraph
import com.hye.presentation.ui.model.BookmarkViewModel
import com.hye.presentation.ui.model.HomeViewModel
import com.hye.presentation.ui.model.SharedViewModel
import com.hye.presentation.ui.screen.tab.GameTabScreen
import com.hye.presentation.ui.screen.tab.HomeTabScreen
import com.hye.presentation.ui.screen.tab.MyPageTabScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedGetBackStackEntry")
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
    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()
    //snackbar 설정
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            MainTopAppBar(currentDestination = currentDestination,
                onBackClick = { navController.popBackStack() })

        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                BottomNavigationItem().renderBottomNavigationItems()

                    .forEachIndexed { _, navigationItem ->
                       val isSelected = currentDestination?.hierarchy?.any {
                           it.route == navigationItem.route::class.qualifiedName
                       } ?:false

                        NavigationBarItem(
                            selected = isSelected,
                            label = {
                                Text(
                                    text = navigationItem.tabName,
                                    color = if (isSelected) MaterialTheme.colorScheme.surfaceVariant
                                    else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(
                                       id =  if (isSelected) navigationItem.selectedIcon
                                        else navigationItem.unSelectedIcon
                                    ),
                                    contentDescription = navigationItem.tabName,
                                    tint = Color.Unspecified
                                )
                            },
                            onClick = {
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent
                            )

                        )
                    }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.background)
        ) {
            NavHost(
                navController = navController,
                startDestination = ScreenRoutDef.TopLevel.HomeTab
            ) {

                composable<ScreenRoutDef.TopLevel.HomeTab> {
                    HomeTabScreen(
                        sharedViewModel= sharedViewModel,
                        homeViewModel = homeViewModel,
                        onNavigateToTodayStudy = { navController.navigate(ScreenRoutDef.HomeFlow.TodayStudyScreen) },
                        snackBarHostState = snackBarHostState
                    )
                }

                composable<ScreenRoutDef.TopLevel.GameTab> {
                    GameTabScreen(
                        sharedViewModel = sharedViewModel,
                        onNavigateToGameScreen = { navController.navigate(ScreenRoutDef.TopLevel.GameTab) },
                        onNavigateToTextToSpeechScreen = { navController.navigate(ScreenRoutDef.GameFlow.TextToSpeechScreen) },
                        onNavigateToSearchScreen = { navController.navigate(ScreenRoutDef.GameFlow.SearchScreen) },
                        onNavigateToVocabularyScreen = { navController.navigate(ScreenRoutDef.GameFlow.VocabularyScreen) },
                        onNavigateToSpeechToTextScreen = { navController.navigate(ScreenRoutDef.GameFlow.SpeechToTextScreen) },
                        onNavigateToDrawScreen = { navController.navigate(ScreenRoutDef.GameFlow.DrawScreen) },
                    )
                }

                composable<ScreenRoutDef.TopLevel.MyPageTab> {
                    MyPageTabScreen(
                        onNavigateToMyPageScreen = { navController.navigate(ScreenRoutDef.TopLevel.MyPageTab) },
                        sharedViewModel = sharedViewModel

                    )
                }

                /* nested-graph*/
                addHomeGraph(
                    sharedViewModel = sharedViewModel,
                    homeViewModel = homeViewModel,
                    snackBarHostState = snackBarHostState,
                    onNavigateToTodayStudyScreen = { navController.navigate(ScreenRoutDef.HomeFlow.TodayStudyScreen) },
                    onNavigateToListenScreen = { navController.navigate(ScreenRoutDef.TodayStudyFlow.ListenScreen) },
                    onNavigateToDictionaryScreen = { navController.navigate(ScreenRoutDef.TodayStudyFlow.DictionaryScreen) },
                    onNavigateToSpeechScreen = { navController.navigate(ScreenRoutDef.TodayStudyFlow.SpeechScreen) },
                    onNavigateToWriteScreen = { navController.navigate(ScreenRoutDef.TodayStudyFlow.WriteScreen) }
                )

                addGameGraph(
                    sharedViewModel = sharedViewModel,
                    onNavigateToDrawScreen = { navController.navigate(ScreenRoutDef.GameFlow.DrawScreen) },
                    onNavigateToSearchScreen = { navController.navigate(ScreenRoutDef.GameFlow.SearchScreen) },
                   // onNavigateToVocabularyScreen = { navController.navigate(ScreenRoutDef.GameFlow.VocabularyScreen) },
                    onNavigateToTextToSpeechScreen = { navController.navigate(ScreenRoutDef.GameFlow.TextToSpeechScreen) },
                    onNavigateToSpeechToTextScreen = { navController.navigate(ScreenRoutDef.GameFlow.SpeechToTextScreen) },
                    onNavigateToDetailScreen = { documentId ->
                        navController.navigate(
                            ScreenRoutDef.GameFlow.DetailVocabularyScreen(
                                documentId
                            )
                        )
                    },
                    bookmarkViewModel = bookmarkViewModel,
                )
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(currentDestination: NavDestination?, onBackClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
        ),
        title = {
            AppBarItems()
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
fun ShowBackButton(currentRoute: NavDestination?): Boolean {
   val topLevelRoutes = listOf(
       ScreenRoutDef.TopLevel.HomeTab::class.qualifiedName,
       ScreenRoutDef.TopLevel.GameTab::class.qualifiedName,
       ScreenRoutDef.TopLevel.MyPageTab::class.qualifiedName
   )
    return currentRoute?.route in topLevelRoutes
    }

@Composable
fun AppBarItems(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PointItem(R.drawable.paw, 100)
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