package org.sopt.collaboration.campuspick.feature.aftersearch

import okhttp3.internal.immutableListOf
import org.sopt.collaboration.campuspick.core.ui.base.SideEffect
import org.sopt.collaboration.campuspick.core.ui.base.UiState
import org.sopt.collaboration.campuspick.domain.model.FilteredClub

data class AfterSearchState(
    val currentKeyword: String = "",
    val currentCategory: String = "",
    val currentDeadLine: String = "",
    val currentRegion: String = "",
    val currentClubDay: String = "",
    val filteredClub: List<FilteredClub> = immutableListOf()
) : UiState

sealed interface AfterSearchSideEffect : SideEffect
