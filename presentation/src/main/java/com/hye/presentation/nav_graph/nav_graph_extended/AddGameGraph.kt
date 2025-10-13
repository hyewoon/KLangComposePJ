package com.hye.presentation.nav_graph.nav_graph_extended


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.hye.presentation.nav_graph.ScreenRoutDef
import com.hye.presentation.ui.model.BookmarkViewModel
import com.hye.presentation.ui.screen.game.DrawScreen
import com.hye.presentation.ui.screen.game.SearchScreen
import com.hye.presentation.ui.screen.game.SpeechToTextScreen
import com.hye.presentation.ui.screen.game.TextToSpeechScreen
import com.hye.presentation.ui.screen.game.VocabularyScreen
import com.hye.presentation.ui.model.SharedViewModel
import com.hye.presentation.ui.screen.game.DetailVocabularyScreen

fun NavGraphBuilder.addGameGraph(
    bookmarkViewModel: BookmarkViewModel,
    sharedViewModel: SharedViewModel,
    onNavigateToDrawScreen: () -> Unit,
    onNavigateToSearchScreen: () -> Unit,
    onNavigateToVocabularyScreen: () -> Unit,
    onNavigateToTextToSpeechScreen: () -> Unit,
    onNavigateToSpeechToTextScreen: () -> Unit,
    onNavigateToDetailScreen: (String) -> Unit,
) {
    navigation<ScreenRoutDef.GameFlow.GameFlowGraph>(
        startDestination = ScreenRoutDef.GameFlow.DrawScreen
    ) {
        composable<ScreenRoutDef.GameFlow.DrawScreen> {
            DrawScreen(
                onNavigateToDrawScreen = onNavigateToDrawScreen,
                sharedViewModel = sharedViewModel,
            )
        }
        composable<ScreenRoutDef.GameFlow.SearchScreen> {
            SearchScreen(
                onNavigateToSearchScreen = onNavigateToSearchScreen,
                sharedViewModel = sharedViewModel
            )
        }
        composable<ScreenRoutDef.GameFlow.TextToSpeechScreen> {
            TextToSpeechScreen(
                onNavigateToTextToSpeechScreen = onNavigateToTextToSpeechScreen,
                sharedViewModel = sharedViewModel
            )
        }
        composable<ScreenRoutDef.GameFlow.SpeechToTextScreen> {
            SpeechToTextScreen(
                onNavigateToSpeechToTextScreen = onNavigateToSpeechToTextScreen,
                sharedViewModel = sharedViewModel
            )

        }
        navigation<ScreenRoutDef.GameFlow.VocabularyScreenGraph>(
            startDestination = ScreenRoutDef.GameFlow.VocabularyScreen
        ){
            composable<ScreenRoutDef.GameFlow.VocabularyScreen> {
                VocabularyScreen(
                    onNavigationToDetailScreen = onNavigateToDetailScreen,
                    bookmarkViewModel = bookmarkViewModel,
                    sharedViewModel = sharedViewModel
                )
            }
            composable<ScreenRoutDef.GameFlow.DetailVocabularyScreen> {backStackEntry->
                val args = backStackEntry.toRoute<ScreenRoutDef.GameFlow.DetailVocabularyScreen>()
                DetailVocabularyScreen(
                    documentId = args.documentId,
                    bookmarkViewModel = bookmarkViewModel,
                    sharedViewModel = sharedViewModel
                )
            }
        }



    }

}