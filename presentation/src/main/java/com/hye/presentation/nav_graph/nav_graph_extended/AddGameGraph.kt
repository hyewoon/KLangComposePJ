package com.hye.presentation.nav_graph.nav_graph_extended


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.hye.presentation.nav_graph.ScreenRoutDef
import com.hye.presentation.ui.screen.game.DrawScreen
import com.hye.presentation.ui.screen.game.SearchScreen
import com.hye.presentation.ui.screen.game.SpeechToTextScreen
import com.hye.presentation.ui.screen.game.TextToSpeechScreen
import com.hye.presentation.ui.screen.game.VocabularyScreen
import com.hye.presentation.ui.screen.model.GameViewModel
import com.hye.presentation.ui.screen.model.SharedViewModel

fun NavGraphBuilder.addGameGraph(
    gameViewModel: GameViewModel,
    sharedViewModel: SharedViewModel,
    onNavigateToDrawScreen: () -> Unit,
    onNavigateToSearchScreen: () -> Unit,
    onNavigateToVocabularyScreen: () -> Unit,
    onNavigateToTextToSpeechScreen: () -> Unit,
    onNavigateToSpeechToTextScreen: () -> Unit,
) {
    navigation<ScreenRoutDef.GameFlow.GameFlowGraph>(
        startDestination = ScreenRoutDef.GameFlow.DrawScreen
    ) {
        composable<ScreenRoutDef.GameFlow.DrawScreen> {
            DrawScreen(
                onNavigateToDrawScreen = onNavigateToDrawScreen,
                gameViewModel = gameViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable<ScreenRoutDef.GameFlow.SearchScreen> {
            SearchScreen(
                onNavigateToSearchScreen = onNavigateToSearchScreen,
                gameViewModel = gameViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable<ScreenRoutDef.GameFlow.VocabularyScreen> {
            VocabularyScreen(
                onNavigateToVocabularyScreen = onNavigateToVocabularyScreen,
                gameViewModel = gameViewModel,
                sharedViewModel = sharedViewModel
            )

        }
        composable<ScreenRoutDef.GameFlow.TextToSpeechScreen> {
            TextToSpeechScreen(
                onNavigateToTextToSpeechScreen = onNavigateToTextToSpeechScreen,
                gameViewModel = gameViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable<ScreenRoutDef.GameFlow.SpeechToTextScreen> {
            SpeechToTextScreen(
                onNavigateToSpeechToTextScreen = onNavigateToSpeechToTextScreen,
                gameViewModel = gameViewModel,
                sharedViewModel = sharedViewModel
            )

        }

    }

}