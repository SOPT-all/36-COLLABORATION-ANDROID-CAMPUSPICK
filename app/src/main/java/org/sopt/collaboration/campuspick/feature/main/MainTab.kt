package org.sopt.collaboration.campuspick.feature.main

import androidx.compose.runtime.Composable
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.navigation.MainTabRoute
import org.sopt.collaboration.campuspick.core.designsystem.navigation.Route

internal enum class MainTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: MainTabRoute,
) {
    HOME(
        iconResId = R.drawable.ic_maintab_home,
        contentDescription = "홈",
        MainTabRoute.Home
    ),
    STUDY(
        iconResId = R.drawable.ic_maintab_study,
        contentDescription = "스터디",
        MainTabRoute.Study
    ),
    COMMUNITY(
        iconResId = R.drawable.ic_maintab_community,
        contentDescription = "커뮤니티",
        MainTabRoute.Community
    ),
    CHATTING(
        iconResId = R.drawable.ic_maintab_chatting,
        contentDescription = "채팅",
        MainTabRoute.Chatting
    ),
    MYPAGE(
        iconResId = R.drawable.ic_maintab_mypage,
        contentDescription = "마이",
        MainTabRoute.Mypage
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}