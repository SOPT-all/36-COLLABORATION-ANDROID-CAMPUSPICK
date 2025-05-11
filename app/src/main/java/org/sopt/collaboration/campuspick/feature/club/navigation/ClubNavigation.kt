package org.sopt.collaboration.campuspick.feature.club.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.collaboration.campuspick.core.designsystem.navigation.Route
import org.sopt.collaboration.campuspick.feature.club.ClubRoute

fun NavController.navigateClub(navOptions: NavOptions) {
    navigate(Route.Club, navOptions)
}

fun NavGraphBuilder.clubNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToSearch: () -> Unit
) {
    composable<Route.Club> {
        ClubRoute(
            navigateToSearch = navigateToSearch
        )
    }
}