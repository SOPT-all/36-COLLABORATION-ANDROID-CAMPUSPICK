package org.sopt.collaboration.campuspick.feature.aftersearch.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.sopt.collaboration.campuspick.core.ui.navigation.Route
import org.sopt.collaboration.campuspick.feature.aftersearch.AfterSearchRoute

fun NavController.navigateAfterSearch(navOptions: NavOptions) {
    navigate(Route.AfterSearch, navOptions)
}

fun NavGraphBuilder.afterSearchNavGraph(
    padding: PaddingValues,
    navigateToBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    composable<Route.AfterSearch> { entry ->
        val keyword = entry.toRoute<Route.AfterSearch>().keyword
        val category = entry.toRoute<Route.AfterSearch>().category
        val deadlineType = entry.toRoute<Route.AfterSearch>().deadlineType
        val region = entry.toRoute<Route.AfterSearch>().region
        val clubDay = entry.toRoute<Route.AfterSearch>().clubDay

        AfterSearchRoute(
            padding = padding,
            keyword = keyword,
            category = category,
            deadline = deadlineType,
            region = region,
            clubDay = clubDay,
            navigateToBack = navigateToBack,
            modifier = modifier
        )
    }
}