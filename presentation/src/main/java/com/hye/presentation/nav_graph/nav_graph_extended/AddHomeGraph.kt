package com.hye.presentation.nav_graph.nav_graph_extended

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.hye.presentation.nav_graph.ScreenRoutDef
import com.hye.presentation.ui.screen.home.DictionaryScreen
import com.hye.presentation.ui.screen.home.ListenScreen
import com.hye.presentation.ui.screen.home.SpeechScreen
import com.hye.presentation.ui.screen.home.TodayStudyScreen
import com.hye.presentation.ui.screen.home.WriteScreen
import com.hye.presentation.ui.model.HomeViewModel
import com.hye.presentation.ui.model.SharedViewModel


@SuppressLint("RememberReturnType")
fun NavGraphBuilder.addHomeGraph(
    homeViewModel: HomeViewModel,
    sharedViewModel: SharedViewModel,
    onNavigateToTodayStudyScreen: () -> Unit,
    onNavigateToListenScreen: () -> Unit,
    onNavigateToDictionaryScreen: () -> Unit,
    onNavigateToSpeechScreen: () -> Unit,
    onNavigateToWriteScreen: () -> Unit,
    snackBarHostState: SnackbarHostState,
) {
    navigation<ScreenRoutDef.HomeFlow.HomeFlowGraph>(
        startDestination = ScreenRoutDef.HomeFlow.TodayStudyScreen
    ) {

        //homeflow
        composable<ScreenRoutDef.HomeFlow.TodayStudyScreen> {
            TodayStudyScreen(
                onNavigateToTodayStudyScreen = onNavigateToTodayStudyScreen,
                onNavigateToListenScreen = onNavigateToListenScreen,
                onNavigateToDictionaryScreen = onNavigateToDictionaryScreen,
                onNavigateToSpeechScreen = onNavigateToSpeechScreen,
                onNavigateToWriteScreen = onNavigateToWriteScreen,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState
            )
        }

        composable<ScreenRoutDef.TodayStudyFlow.ListenScreen> {
            ListenScreen(
                onNavigateToListenScreen = onNavigateToListenScreen,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState
            )

        }
        composable<ScreenRoutDef.TodayStudyFlow.DictionaryScreen> {
            DictionaryScreen(
                onNavigateToDictionaryScreen = onNavigateToDictionaryScreen,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState
            )


        }
        composable<ScreenRoutDef.TodayStudyFlow.SpeechScreen> {
            SpeechScreen(
                onNavigateToSpeechScreen = onNavigateToSpeechScreen,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState
            )


        }
        composable<ScreenRoutDef.TodayStudyFlow.WriteScreen> {
            WriteScreen(
                onNavigateToWriteScreen = onNavigateToWriteScreen,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState
            )


        }
    }

}




