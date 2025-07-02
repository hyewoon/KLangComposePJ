package com.hye.klangcomposepj.nav_graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.hye.klangcomposepj.R

data class BottomNavigationItem(
    val tabName: String = "",
    val icon: Int = R.drawable.home_selected,
    val route: String = "",
) {
    fun renderBottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                tabName = ScreenRoutDef.TopLevel.HomeTab.routeName,
                icon = R.drawable.home_selected,
                route = ScreenRoutDef.TopLevel.HomeTab.routeName
            ),
            BottomNavigationItem(
                tabName = ScreenRoutDef.TopLevel.GameTab.routeName,
                icon = R.drawable.game_selected,
                route = ScreenRoutDef.TopLevel.GameTab.routeName

            ),
            BottomNavigationItem(
                tabName = ScreenRoutDef.TopLevel.MyPageTab.routeName,
                icon = R.drawable.my_selected,
                route = ScreenRoutDef.TopLevel.MyPageTab.routeName
            )
        )

    }
}