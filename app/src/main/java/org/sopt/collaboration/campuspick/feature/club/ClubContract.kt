package org.sopt.collaboration.campuspick.feature.club

import org.sopt.collaboration.campuspick.core.ui.base.SideEffect
import org.sopt.collaboration.campuspick.core.ui.base.UiState

data class ClubState(
    val selectedTabIndex: Int = 0,
    val inputSearch: String = ""
) : UiState

sealed class ClubSideEffect : SideEffect