package org.sopt.collaboration.campuspick.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.collaboration.campuspick.core.designsystem.navigation.Route
import org.sopt.collaboration.campuspick.domain.model.SearchType
import org.sopt.collaboration.campuspick.feature.home.navigation.navigateHome
import org.sopt.collaboration.campuspick.feature.others.navigation.navigateChatting
import org.sopt.collaboration.campuspick.feature.others.navigation.navigateCommunity
import org.sopt.collaboration.campuspick.feature.others.navigation.navigateMypage
import org.sopt.collaboration.campuspick.feature.others.navigation.navigateStudy

internal class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = MainTab.HOME.route

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (tab) {
            MainTab.HOME -> navController.navigateHome(navOptions)
            MainTab.STUDY -> navController.navigateStudy(navOptions)
            MainTab.COMMUNITY -> navController.navigateCommunity(navOptions)
            MainTab.CHATTING -> navController.navigateChatting(navOptions)
            MainTab.MYPAGE -> navController.navigateMypage(navOptions)
        }
    }

    @Composable
    fun setBottomBarVisibility() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToClub() {
        navController.navigate(Route.Club)
    }

    fun navigateToSearch() {
        navController.navigate(Route.Search)
    }

    fun navigateToAfterSearch(searchType: SearchType) {
        navController.navigate(
            Route.AfterSearch(
                keyword = searchType.keyword,
                category = searchType.category,
                deadlineType = searchType.deadlineType,
                region = searchType.region,
                clubDay = searchType.clubDay
            )
        )
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}