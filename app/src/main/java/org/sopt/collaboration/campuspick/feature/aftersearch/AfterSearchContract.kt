package org.sopt.collaboration.campuspick.feature.aftersearch

import okhttp3.internal.immutableListOf
import org.sopt.collaboration.campuspick.core.ui.base.SideEffect
import org.sopt.collaboration.campuspick.core.ui.base.UiState
import org.sopt.collaboration.campuspick.domain.model.FilteredClub
import org.sopt.collaboration.campuspick.domain.model.SearchType

data class AfterSearchState(
    val selectFilterIcon: Boolean = false,
    val showFilterBottomSheet: Boolean = false,
    val currentFilter: SearchType = SearchType(
        keyword = null,
        category = null,
        deadline = null,
        region = null,
        clubDay = null
    ),
    val filteredClub: List<FilteredClub> = immutableListOf(),
    val clubLoadState: ClubLoadState = ClubLoadState.Loading
) : UiState

sealed interface AfterSearchSideEffect : SideEffect {
    data object NavigateBack : AfterSearchSideEffect
}

sealed interface ClubLoadState {
    data object Loading : ClubLoadState
    data class Success(val filteredClubs: List<FilteredClub>) : ClubLoadState
}