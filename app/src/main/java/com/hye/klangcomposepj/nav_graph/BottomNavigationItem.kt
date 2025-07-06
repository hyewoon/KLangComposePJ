package com.hye.klangcomposepj.nav_graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.hye.klangcomposepj.R

data class BottomNavigationItem(
    val tabName: String = "",
    val selectedIcon: Int = R.drawable.home_selected,
    val unSelectedIcon: Int = R.drawable.home_unselected,
    val route: String = "",
) {
    fun renderBottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                tabName = ScreenRoutDef.TopLevel.HomeTab.routeName,
                selectedIcon = R.drawable.home_selected,
                unSelectedIcon = R.drawable.home_unselected,
                route = ScreenRoutDef.TopLevel.HomeTab.routeName
            ),
            BottomNavigationItem(
                tabName = ScreenRoutDef.TopLevel.GameTab.routeName,
                selectedIcon = R.drawable.game_selected,
                unSelectedIcon = R.drawable.game_unselected,
                route = ScreenRoutDef.TopLevel.GameTab.routeName

            ),
            BottomNavigationItem(
                tabName = ScreenRoutDef.TopLevel.MyPageTab.routeName,
                selectedIcon = R.drawable.my_selected,
                unSelectedIcon = R.drawable.my_unselected,
                route = ScreenRoutDef.TopLevel.MyPageTab.routeName
            )
        )

    }
}