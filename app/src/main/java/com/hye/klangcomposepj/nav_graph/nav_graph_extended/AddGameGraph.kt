package com.hye.klangcomposepj.nav_graph.nav_graph_extended

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.hye.klangcomposepj.nav_graph.ScreenRoutDef
import com.hye.klangcomposepj.ui.screen.game.DrawScreen
import com.hye.klangcomposepj.ui.screen.game.SearchScreen
import com.hye.klangcomposepj.ui.screen.game.SpeechToTextScreen
import com.hye.klangcomposepj.ui.screen.game.TextToSpeechScreen
import com.hye.klangcomposepj.ui.screen.game.VocabularyScreen

fun NavGraphBuilder.addGameGraph(navController: NavController){
    navigation(
        startDestination = ScreenRoutDef.GameFlow.DrawScreen.routeName,
        route = "game_flow_graph"
    ){
        composable(ScreenRoutDef.GameFlow.DrawScreen.routeName){
            DrawScreen(navController)
        }
        composable(ScreenRoutDef.GameFlow.SearchScreen.routeName){
            SearchScreen(navController)
        }
        composable(ScreenRoutDef.GameFlow.VocabularyScreen.routeName){
            VocabularyScreen(navController)

        }
        composable(ScreenRoutDef.GameFlow.TextToSpeechScreen.routeName){
            TextToSpeechScreen(navController)
        }
        composable(ScreenRoutDef.GameFlow.SpeechToTextScreen.routeName){
            SpeechToTextScreen(navController)

        }

    }

}