package org.sopt.collaboration.campuspick.feature.home

import org.sopt.collaboration.campuspick.core.ui.base.SideEffect
import org.sopt.collaboration.campuspick.core.ui.base.UiState
import org.sopt.collaboration.campuspick.domain.model.PopularActivity

data class HomeState(
    val popularActivity: List<PopularActivity> = emptyList(),
): UiState

sealed interface HomeSideEffect: SideEffect{
    data object NavigateClub: HomeSideEffect
}