package org.sopt.collaboration.campuspick.feature.search

import org.sopt.collaboration.campuspick.core.ui.base.SideEffect
import org.sopt.collaboration.campuspick.core.ui.base.UiState
import org.sopt.collaboration.campuspick.domain.model.DeadLine
import org.sopt.collaboration.campuspick.domain.model.Location
import org.sopt.collaboration.campuspick.domain.model.PreferDay

data class SearchState(
    val inputSearch: String = "",
    val showFilterBottomSheet: Boolean = false,
    val filterDeadLine: DeadLine = DeadLine.EMPTY,
    val filterLocation: Location = Location.EMPTY,
    val filterPreferDay: PreferDay = PreferDay.EMPTY,
) : UiState

sealed interface SearchSideEffect : SideEffect