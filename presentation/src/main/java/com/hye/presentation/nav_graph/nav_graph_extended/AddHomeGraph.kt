package com.hye.presentation.nav_graph.nav_graph_extended

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {


    navigation(
        startDestination = ScreenRoutDef.HomeFlow.TodayStudyScreen.routeName,
        route = "home_flow_graph"
    ) {
        composable(ScreenRoutDef.HomeFlow.TodayStudyScreen.routeName) {
            val homeTabEntry = remember(it) {
                navController.getBackStackEntry(ScreenRoutDef.TopLevel.HomeTab.routeName)
            }
            val homeViewModel: HomeViewModel = hiltViewModel(homeTabEntry)
            TodayStudyScreen(
                navController = navController,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel
            )

        }

        composable(ScreenRoutDef.TodayStudyFlow.ListenScreen.routeName) {
            val homeTabEntry = remember(it) {
                navController.getBackStackEntry(ScreenRoutDef.TopLevel.HomeTab.routeName)
            }
            val homeViewModel: HomeViewModel = hiltViewModel(homeTabEntry)
            ListenScreen(
                navController = navController,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable(ScreenRoutDef.TodayStudyFlow.DictionaryScreen.routeName) {
            val homeTabEntry = remember(it) {
                navController.getBackStackEntry(ScreenRoutDef.TopLevel.HomeTab.routeName)
            }
            val homeViewModel: HomeViewModel = hiltViewModel(homeTabEntry)
            DictionaryScreen(
                navController = navController,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel)

        }
        composable(ScreenRoutDef.TodayStudyFlow.SpeechScreen.routeName) {
            val homeTabEntry = remember(it) {
                navController.getBackStackEntry(ScreenRoutDef.TopLevel.HomeTab.routeName)
            }
            val homeViewModel: HomeViewModel = hiltViewModel(homeTabEntry)
            SpeechScreen( navController = navController,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel)
        }
        composable(ScreenRoutDef.TodayStudyFlow.WriteScreen.routeName) {
            val homeTabEntry = remember(it) {
                navController.getBackStackEntry(ScreenRoutDef.TopLevel.HomeTab.routeName)
            }
            val homeViewModel: HomeViewModel = hiltViewModel(homeTabEntry)
            WriteScreen( navController = navController,
                homeViewModel = homeViewModel,
                sharedViewModel = sharedViewModel)

        }
    }
}




