package com.hye.presentation.ui.component.bottom_navigationbar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.hye.presentation.nav_graph.BottomNavigationItem


@Composable
fun MainBottomNavigationBar(
    navController : NavHostController,
    navBackStackEntry: NavBackStackEntry? = null,
    currentDestination: NavDestination? = null
){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        BottomNavigationItem().renderBottomNavigationItems()

            .forEachIndexed { _, navigationItem ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == navigationItem.route::class.qualifiedName
                } ?: false

                NavigationBarItem(
                    selected = isSelected,
                    label = {
                        Text(
                            text = navigationItem.tabName,
                            color = if (isSelected) MaterialTheme.colorScheme.surfaceVariant
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = if (isSelected) navigationItem.selectedIcon
                                else navigationItem.unSelectedIcon
                            ),
                            contentDescription = navigationItem.tabName,
                            tint = Color.Unspecified
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
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )

                )
            }
    }


}