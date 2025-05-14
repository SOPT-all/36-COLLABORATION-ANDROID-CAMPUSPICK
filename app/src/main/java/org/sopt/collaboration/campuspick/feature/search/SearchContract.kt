package org.sopt.collaboration.campuspick.feature.search

import org.sopt.collaboration.campuspick.core.ui.base.SideEffect
import org.sopt.collaboration.campuspick.core.ui.base.UiState

data class SearchState(
    val inputSearch: String = ""
) : UiState

sealed interface SearchSideEffect : SideEffect