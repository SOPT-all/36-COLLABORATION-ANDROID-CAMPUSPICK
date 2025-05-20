package org.sopt.collaboration.campuspick.feature.search

import org.sopt.collaboration.campuspick.core.ui.base.SideEffect
import org.sopt.collaboration.campuspick.core.ui.base.UiState
import org.sopt.collaboration.campuspick.domain.model.DeadLine
import org.sopt.collaboration.campuspick.domain.model.PreferDay
import org.sopt.collaboration.campuspick.domain.model.Region

data class SearchState(
    val inputSearch: String = "",
    val showFilterBottomSheet: Boolean = false,
    val filterDeadLine: DeadLine = DeadLine.EMPTY,
    val filterRegion: Region = Region.EMPTY,
    val filterPreferDay: PreferDay = PreferDay.EMPTY,
) : UiState

sealed interface SearchSideEffect : SideEffect {
    data object NavigateAfterSearch : SearchSideEffect
    data object NavigateBack : SearchSideEffect
}