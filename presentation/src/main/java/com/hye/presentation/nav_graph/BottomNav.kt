package com.hye.presentation.nav_graph

import kotlinx.serialization.Serializable

@Serializable
sealed interface BottomNav {

    @Serializable
    data object Home : BottomNav

    @Serializable
    data object Game : BottomNav

    @Serializable
    data object MyPage : BottomNav

}