package com.hye.presentation.nav_graph.nav_graph_extended

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.hye.presentation.nav_graph.ScreenRoutDef
import com.hye.presentation.ui.screen.home.DictionaryScreen
import com.hye.presentation.ui.screen.home.ListenScreen
import com.hye.presentation.ui.screen.home.SpeechScreen
import com.hye.presentation.ui.screen.home.TodayStudyScreen
import com.hye.presentation.ui.screen.home.WriteScreen
import com.hye.presentation.ui.screen.model.HomeViewModel
import com.hye.presentation.ui.screen.model.SharedViewModel

fun NavGraphBuilder.addHomeGraph(
    getHomeTabEntry: () -> NavBackStackEntry,
    onNavigateToTodayStudyScreen: () -> Unit,
    onNavigateToListenScreen: () -> Unit,
    onNavigateToDictionaryScreen: () -> Unit,
    onNavigateToSpeechScreen: () -> Unit,
    onNavigateToWriteScreen: () -> Unit,
    sharedViewModel: SharedViewModel,
    snackBarHostState: SnackbarHostState
) {


    navigation<ScreenRoutDef.HomeFlow>(
        startDestination = ScreenRoutDef.HomeFlow.TodayStudyScreen
    ) {
        composable<ScreenRoutDef.HomeFlow.TodayStudyScreen> {
            val homeTabEntry = remember(it) { getHomeTabEntry() }
            val homeViewModel: HomeViewModel = hiltViewModel(homeTabEntry)

            TodayStudyScreen(
                onNavigateToListenScreen = onNavigateToListenScreen,
                onNavigateToDictionaryScreen = onNavigateToDictionaryScreen,
                onNavigateToSpeechScreen = onNavigateToSpeechScreen,
                onNavigateToWriteScreen = onNavigateToWriteScreen,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel,
                snackBarHostState =snackBarHostState
            )

        }

        composable<ScreenRoutDef.TodayStudyFlow.ListenScreen> {
            val homeTabEntry = remember(it) { getHomeTabEntry() }
            val homeViewModel: HomeViewModel = hiltViewModel(homeTabEntry)
            ListenScreen(
                onNavigateToListenScreen =onNavigateToListenScreen ,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState
            )
        }
        composable<ScreenRoutDef.TodayStudyFlow.DictionaryScreen> {
            val homeTabEntry = remember(it) { getHomeTabEntry() }
            val homeViewModel: HomeViewModel = hiltViewModel(homeTabEntry)
            DictionaryScreen(
                onNavigateToDictionaryScreen= onNavigateToDictionaryScreen,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState
            )

        }
        composable<ScreenRoutDef.TodayStudyFlow.SpeechScreen> {
            val homeTabEntry = remember(it) { getHomeTabEntry() }
            val homeViewModel: HomeViewModel = hiltViewModel(homeTabEntry)
            SpeechScreen(
                onNavigateToSpeechScreen = onNavigateToSpeechScreen,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState)
        }
        composable<ScreenRoutDef.TodayStudyFlow.WriteScreen> {
            val homeTabEntry = remember(it) { getHomeTabEntry() }
            val homeViewModel: HomeViewModel = hiltViewModel(homeTabEntry)
            WriteScreen(
                onNavigateToWriteScreen = onNavigateToWriteScreen,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState)

        }
    }
}




