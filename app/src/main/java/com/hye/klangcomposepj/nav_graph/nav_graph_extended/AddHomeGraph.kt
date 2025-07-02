package com.hye.klangcomposepj.nav_graph.nav_graph_extended

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.hye.klangcomposepj.nav_graph.ScreenRoutDef
import com.hye.klangcomposepj.ui.screen.home.DictionaryScreen
import com.hye.klangcomposepj.ui.screen.home.ListenScreen
import com.hye.klangcomposepj.ui.screen.home.SpeechScreen
import com.hye.klangcomposepj.ui.screen.home.TodayStudyScreen
import com.hye.klangcomposepj.ui.screen.home.WriteScreen

fun NavGraphBuilder.addHomeGraph(navController: NavController) {


    navigation(
        startDestination = ScreenRoutDef.HomeFlow.TodayStudyScreen.routeName,
        route = "home_flow_graph"
    ) {
        composable(ScreenRoutDef.HomeFlow.TodayStudyScreen.routeName) {
            TodayStudyScreen(navController)
        }

        composable(ScreenRoutDef.TodayStudyFlow.ListenScreen.routeName) {
            ListenScreen(navController)
        }
        composable(ScreenRoutDef.TodayStudyFlow.DictionaryScreen.routeName) {
            DictionaryScreen(navController)

        }
        composable(ScreenRoutDef.TodayStudyFlow.SpeechScreen.routeName) {
            SpeechScreen(navController)
        }
        composable(ScreenRoutDef.TodayStudyFlow.WriteScreen.routeName) {
            WriteScreen(navController)

        }
    }
}




