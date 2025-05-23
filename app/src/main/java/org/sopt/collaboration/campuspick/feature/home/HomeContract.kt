package org.sopt.collaboration.campuspick.feature.home

import org.sopt.collaboration.campuspick.core.ui.base.SideEffect
import org.sopt.collaboration.campuspick.core.ui.base.UiState
import org.sopt.collaboration.campuspick.domain.model.Popularity

data class HomeState(
    val popularContestList: List<Popularity> = emptyList()
): UiState

sealed interface HomeSideEffect: SideEffect{
    data object NavigateClub: HomeSideEffect
}