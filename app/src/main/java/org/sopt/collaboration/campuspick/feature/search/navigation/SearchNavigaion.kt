package org.sopt.collaboration.campuspick.feature.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.collaboration.campuspick.core.designsystem.navigation.Route
import org.sopt.collaboration.campuspick.feature.search.SearchRoute

fun NavController.navigateSearch(navOptions: NavOptions) {
    navigate(Route.Search, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    composable<Route.Search> {
        SearchRoute(
            padding = padding,
            modifier = modifier
        )
    }
}