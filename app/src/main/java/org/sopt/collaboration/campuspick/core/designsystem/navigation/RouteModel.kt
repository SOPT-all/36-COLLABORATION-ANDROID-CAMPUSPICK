package org.sopt.collaboration.campuspick.core.designsystem.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Club : Route

    @Serializable
    data object Search : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object Study : MainTabRoute

    @Serializable
    data object Community : MainTabRoute

    @Serializable
    data object Chatting : MainTabRoute

    @Serializable
    data object Mypage : MainTabRoute


}