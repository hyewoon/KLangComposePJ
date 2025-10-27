package com.hye.presentation.nav_graph.nav_graph_extended

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.hye.presentation.nav_graph.ScreenRoutDef
import com.hye.presentation.ui.screen.home.DictionaryScreen
import com.hye.presentation.ui.screen.home.ListenScreen
import com.hye.presentation.ui.screen.home.SpeechScreen
import com.hye.presentation.ui.screen.home.TodayStudyScreen
import com.hye.presentation.ui.screen.home.WriteScreen
import com.hye.presentation.ui.model.HomeViewModel
import com.hye.presentation.ui.model.SharedViewModel
import com.hye.presentation.ui.model.TTSViewModel


@SuppressLint("RememberReturnType")
fun NavGraphBuilder.addHomeGraph(
    homeViewModel: HomeViewModel,
    sharedViewModel: SharedViewModel,
    ttsViewModel: TTSViewModel,
    onNavigateToTodayStudyScreen: () -> Unit,
    onNavigateToListenScreen: (String, String) -> Unit,
    onNavigateToDictionaryScreen: () -> Unit,
    onNavigateToSpeechScreen: (String, String) -> Unit,
    onNavigateToWriteScreen: (String,String) -> Unit,
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
                onNavigateToWriteScreen = onNavigateToWriteScreen
                ,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState
            )
        }

        composable<ScreenRoutDef.TodayStudyFlow.ListenScreen> {

            val args = it.toRoute<ScreenRoutDef.TodayStudyFlow.ListenScreen>()
            ListenScreen(
                korean = args.korean,
                english= args.english,
                onNavigateToListenScreen = onNavigateToListenScreen,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState,
                ttsViewModel = ttsViewModel
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
            val args= it.toRoute<ScreenRoutDef.TodayStudyFlow.SpeechScreen>()
            SpeechScreen(
                korean = args.korean,
                english = args.english,
                onNavigateToSpeechScreen = onNavigateToSpeechScreen,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState
            )


        }
        composable<ScreenRoutDef.TodayStudyFlow.WriteScreen> {
            val args = it.toRoute<ScreenRoutDef.TodayStudyFlow.WriteScreen>()

            WriteScreen(
                korean = args.korean,
                english = args.english,
                onNavigateToWriteScreen = onNavigateToWriteScreen,
                sharedViewModel = sharedViewModel,
                snackBarHostState = snackBarHostState
            )


        }
    }

}




