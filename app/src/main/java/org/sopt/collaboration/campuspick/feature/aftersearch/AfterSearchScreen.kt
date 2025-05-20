package org.sopt.collaboration.campuspick.feature.aftersearch

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.collaboration.campuspick.core.ui.lifecycle.LaunchedEffectWithLifecycle
import org.sopt.collaboration.campuspick.core.viewmodel.ViewModelFactory

@Composable
fun AfterSearchRoute(
    padding: PaddingValues,
    keyword: String?,
    category: String?,
    deadlineType: String?,
    region: String?,
    clubDay: String?,
    navigateToBack: () -> Unit,
    modifier: Modifier,
    viewModel: AfterSearchViewModel = viewModel(factory = ViewModelFactory())
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffectWithLifecycle {
        viewModel.getFilteredClub(
            keyword = keyword,
            category = category,
            deadlineType = deadlineType,
            region = region,
            clubDay = clubDay
        )
    }
    
    AfterSearchScreen(
    )
}

@Composable
fun AfterSearchScreen() {
}