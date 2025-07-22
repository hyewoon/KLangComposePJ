package com.hye.presentation.nav_graph.nav_graph_extended

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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

fun NavGraphBuilder.addGameGraph(navController: NavController,
                                 sharedViewModel: SharedViewModel
                                 ){
    navigation(
        startDestination = ScreenRoutDef.GameFlow.DrawScreen.routeName,
        route = "game_flow_graph"
    ){
        composable(ScreenRoutDef.GameFlow.DrawScreen.routeName){
            val gameTabEntry = remember(it) {
                navController.getBackStackEntry(ScreenRoutDef.TopLevel.GameTab.routeName)
            }
            val gameViewModel: GameViewModel = hiltViewModel(gameTabEntry)
            DrawScreen(navController = navController,
                gameViewModel = gameViewModel,
                sharedViewModel = sharedViewModel)
        }
        composable(ScreenRoutDef.GameFlow.SearchScreen.routeName){
            val gameTabEntry = remember(it) {
                navController.getBackStackEntry(ScreenRoutDef.TopLevel.GameTab.routeName)
            }
            val gameViewModel: GameViewModel = hiltViewModel(gameTabEntry)
            SearchScreen(navController = navController,
                gameViewModel = gameViewModel,
                sharedViewModel = sharedViewModel)
        }
        composable(ScreenRoutDef.GameFlow.VocabularyScreen.routeName){
            val gameTabEntry = remember(it) {
                navController.getBackStackEntry(ScreenRoutDef.TopLevel.GameTab.routeName)
            }
            val gameViewModel: GameViewModel = hiltViewModel(gameTabEntry)
            VocabularyScreen(navController = navController,
                gameViewModel = gameViewModel,
                sharedViewModel = sharedViewModel)

        }
        composable(ScreenRoutDef.GameFlow.TextToSpeechScreen.routeName){
            val gameTabEntry = remember(it) {
                navController.getBackStackEntry(ScreenRoutDef.TopLevel.GameTab.routeName)
            }
            val gameViewModel: GameViewModel = hiltViewModel(gameTabEntry)
            TextToSpeechScreen(navController = navController,
                gameViewModel = gameViewModel,
                sharedViewModel = sharedViewModel)
        }
        composable(ScreenRoutDef.GameFlow.SpeechToTextScreen.routeName){
            val gameTabEntry = remember(it) {
                navController.getBackStackEntry(ScreenRoutDef.TopLevel.GameTab.routeName)
            }
            val gameViewModel: GameViewModel = hiltViewModel(gameTabEntry)
            SpeechToTextScreen(navController = navController,
                gameViewModel = gameViewModel,
                sharedViewModel = sharedViewModel)

        }

    }

}