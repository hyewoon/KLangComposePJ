package com.hye.presentation.nav_graph

import com.hye.presentation.R
import kotlinx.serialization.Serializable

@Serializable
data class BottomNavigationItem(
    val tabName: String ="" ,
    val selectedIcon: Int = R.drawable.home_selected,
    val unSelectedIcon: Int = R.drawable.home_unselected,
    val route: ScreenRoutDef = ScreenRoutDef.TopLevel.HomeTab,
) {
    fun renderBottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                tabName = "HomeTab",
                selectedIcon = R.drawable.home_selected,
                unSelectedIcon = R.drawable.home_unselected,
                route = ScreenRoutDef.TopLevel.HomeTab
            ),
            BottomNavigationItem(
                tabName ="GameTab",
                selectedIcon = R.drawable.game_selected,
                unSelectedIcon = R.drawable.game_unselected,
                route = ScreenRoutDef.TopLevel.GameTab

            ),
            BottomNavigationItem(
                tabName = "MyPageTab",
                selectedIcon = R.drawable.my_selected,
                unSelectedIcon = R.drawable.my_unselected,
                route = ScreenRoutDef.TopLevel.MyPageTab
            )
        )

    }
}