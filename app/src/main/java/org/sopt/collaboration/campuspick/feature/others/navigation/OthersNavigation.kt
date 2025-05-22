package org.sopt.collaboration.campuspick.feature.others.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.collaboration.campuspick.core.ui.navigation.MainTabRoute
import org.sopt.collaboration.campuspick.feature.others.ChattingRoute
import org.sopt.collaboration.campuspick.feature.others.CommunityRoute
import org.sopt.collaboration.campuspick.feature.others.MypageRoute
import org.sopt.collaboration.campuspick.feature.others.StudyRoute

fun NavController.navigateStudy(navOptions: NavOptions) {
    navigate(MainTabRoute.Study, navOptions)
}

fun NavController.navigateCommunity(navOptions: NavOptions) {
    navigate(MainTabRoute.Community, navOptions)
}

fun NavController.navigateChatting(navOptions: NavOptions) {
    navigate(MainTabRoute.Chatting, navOptions)
}

fun NavController.navigateMypage(navOptions: NavOptions) {
    navigate(MainTabRoute.Mypage, navOptions)
}

fun NavGraphBuilder.studyNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    composable<MainTabRoute.Study> {
        StudyRoute()
    }
}

fun NavGraphBuilder.communityNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    composable<MainTabRoute.Community> {
        CommunityRoute()
    }
}

fun NavGraphBuilder.chattingNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    composable<MainTabRoute.Chatting> {
        ChattingRoute()
    }
}

fun NavGraphBuilder.mypageNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    composable<MainTabRoute.Mypage> {
        MypageRoute()
    }
}