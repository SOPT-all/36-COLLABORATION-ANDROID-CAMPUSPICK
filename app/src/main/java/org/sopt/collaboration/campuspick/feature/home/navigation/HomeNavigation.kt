package org.sopt.collaboration.campuspick.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.collaboration.campuspick.core.ui.navigation.MainTabRoute
import org.sopt.collaboration.campuspick.feature.home.HomeRoute

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(MainTabRoute.Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToClub: () -> Unit,
) {
    composable<MainTabRoute.Home> {
        HomeRoute(
            navigateToClub = navigateToClub
        )
    }
}