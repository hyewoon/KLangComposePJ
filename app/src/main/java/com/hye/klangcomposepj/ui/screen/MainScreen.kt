package com.hye.klangcomposepj.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hye.klangcomposepj.R
import com.hye.klangcomposepj.nav_graph.BottomNavigationItem
import com.hye.klangcomposepj.nav_graph.ScreenRoutDef
import com.hye.klangcomposepj.nav_graph.nav_graph_extended.addGameGraph
import com.hye.klangcomposepj.nav_graph.nav_graph_extended.addHomeGraph
import com.hye.klangcomposepj.ui.screen.tab.GameTabScreen
import com.hye.klangcomposepj.ui.screen.tab.HomeTabScreen
import com.hye.klangcomposepj.ui.screen.tab.MyPageTabScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    Scaffold(
        topBar = {
            MainTopAppBar(currentRoute = currentDestination?.route,
                onBackClick = { navController.popBackStack() })

        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                BottomNavigationItem().renderBottomNavigationItems()

                    .forEachIndexed { _, navigationItem ->
                        NavigationBarItem(
                            selected = navigationItem.route == currentDestination?.route,
                            label = {
                                Text(
                                    text = navigationItem.tabName,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = navigationItem.icon),
                                    contentDescription = navigationItem.tabName,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            onClick = {
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },

                            )
                    }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.background)
        ) {
            NavHost(
                navController = navController,
                startDestination = ScreenRoutDef.TopLevel.HomeTab.routeName
            ) {
                composable(ScreenRoutDef.TopLevel.HomeTab.routeName) {
                    HomeTabScreen(navController)
                }
                composable(ScreenRoutDef.TopLevel.GameTab.routeName) {
                    GameTabScreen(navController)
                }
                composable(ScreenRoutDef.TopLevel.MyPageTab.routeName) {
                    MyPageTabScreen(
                        navController
                    )
                }
                /*
                * nested_graph*/
                addHomeGraph(navController)
                addGameGraph(navController)
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(currentRoute: String?, onBackClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
        ),
        title = {
            AppBarItems()
        },
        navigationIcon = {
               if(!(ShowBackButton(currentRoute))){
                   IconButton(onClick = onBackClick) {
                       Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
                   }
               }
        }
    )
}

@Composable
fun ShowBackButton(currentRoute: String?):Boolean {
    val topLevelRoutes = listOf(
        ScreenRoutDef.TopLevel.HomeTab.routeName,
        ScreenRoutDef.TopLevel.GameTab.routeName,
        ScreenRoutDef.TopLevel.MyPageTab.routeName
    )
    return currentRoute in topLevelRoutes
}

@Composable
fun AppBarItems(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PointItem(R.drawable.paw, 100)
        Spacer(modifier = Modifier.width(20.dp))
        PointItem(R.drawable.point, 200)
    }

}

@Composable
fun PointItem(
    iconResId: Int,
    point: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = "point_icon",
            tint = Color.Unspecified,
            modifier = modifier
        )
        Spacer(modifier.width(16.dp))
        Text(
            text = point.toString(),
            modifier = modifier,
            fontSize = MaterialTheme.typography.bodySmall.fontSize
        )
    }


}