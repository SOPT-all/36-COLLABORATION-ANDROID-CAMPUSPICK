package org.sopt.collaboration.campuspick.feature.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.feature.club.navigation.clubNavGraph
import org.sopt.collaboration.campuspick.feature.home.navigation.homeNavGraph
import org.sopt.collaboration.campuspick.feature.main.MainNavigator
import org.sopt.collaboration.campuspick.feature.others.navigation.chattingNavGraph
import org.sopt.collaboration.campuspick.feature.others.navigation.communityNavGraph
import org.sopt.collaboration.campuspick.feature.others.navigation.mypageNavGraph
import org.sopt.collaboration.campuspick.feature.others.navigation.studyNavGraph
import org.sopt.collaboration.campuspick.feature.search.navigation.searchNavGraph

@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CampuspickTheme.colors.White)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            homeNavGraph(
                padding = padding,
                navigateToClub = navigator::navigateToClub
            )
            clubNavGraph(
                padding = padding,
                navigateBack = navigator::navigateBack,
                navigateToSearch = navigator::navigateToSearch
            )
            searchNavGraph(padding = padding)

            //dummy
            studyNavGraph(padding = padding)
            communityNavGraph(padding = padding)
            chattingNavGraph(padding = padding)
            mypageNavGraph(padding = padding)
        }
    }
}