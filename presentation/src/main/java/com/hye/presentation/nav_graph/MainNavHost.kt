package com.hye.presentation.nav_graph

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hye.presentation.nav_graph.nav_graph_extended.addGameGraph
import com.hye.presentation.nav_graph.nav_graph_extended.addHomeGraph
import com.hye.presentation.ui.model.BookmarkViewModel
import com.hye.presentation.ui.model.HomeViewModel
import com.hye.presentation.ui.model.SharedViewModel
import com.hye.presentation.ui.model.TTSViewModel
import com.hye.presentation.ui.screen.tab.GameTabScreen
import com.hye.presentation.ui.screen.tab.HomeTabScreen
import com.hye.presentation.ui.screen.tab.MyPageTabScreen


@Composable
fun MainNavHost(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    homeViewModel: HomeViewModel,
    bookmarkViewModel: BookmarkViewModel,
    ttsViewModel: TTSViewModel,

){
    //snackbar 설정
    val snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
    NavHost(
        navController = navController,
        startDestination = ScreenRoutDef.TopLevel.HomeTab
    ) {

        composable<ScreenRoutDef.TopLevel.HomeTab> {
            HomeTabScreen(
                sharedViewModel = sharedViewModel,
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
            ttsViewModel = ttsViewModel,
            snackBarHostState = snackBarHostState,
            onNavigateToTodayStudyScreen = { navController.navigate(ScreenRoutDef.HomeFlow.TodayStudyScreen) },
            onNavigateToListenScreen = { korean, english ->
                navController.navigate(ScreenRoutDef.TodayStudyFlow.ListenScreen(
                    korean, english
                )) },
            onNavigateToDictionaryScreen = { navController.navigate(ScreenRoutDef.TodayStudyFlow.DictionaryScreen) },
            onNavigateToSpeechScreen = { korean, english ->
                navController.navigate(
                    ScreenRoutDef.TodayStudyFlow.SpeechScreen(
                        korean, english
                    )
                )
            },
            onNavigateToWriteScreen = { korean, english ->
                navController.navigate(
                    ScreenRoutDef.TodayStudyFlow.WriteScreen(
                        korean, english
                    )
                )
            },
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
            onNavigateToSearchDetailScreen = {targetCode ->
                navController.navigate(ScreenRoutDef.GameFlow.SearchDetailScreen(targetCode))
            },
        )
    }


}