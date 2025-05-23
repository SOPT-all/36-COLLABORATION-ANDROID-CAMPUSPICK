package org.sopt.collaboration.campuspick.feature.search

import org.sopt.collaboration.campuspick.core.ui.base.BaseViewModel
import org.sopt.collaboration.campuspick.core.ui.model.DeadLine
import org.sopt.collaboration.campuspick.core.ui.model.ClubDay
import org.sopt.collaboration.campuspick.core.ui.model.Region

class SearchViewModel : BaseViewModel<SearchState, SearchSideEffect>(SearchState()) {

    fun updateInputSearch(inputSearch: String) {
        intent {
            copy(
                inputSearch = inputSearch
            )
        }
    }

    fun updateBottomSheetShown(shown: Boolean) {
        intent {
            copy(showFilterBottomSheet = shown)
        }
    }

    fun updateSelectedDeadLine(deadLine: DeadLine) {
        intent {
            copy(
                filterDeadLine = if (uiState.value.filterDeadLine == deadLine) {
                    DeadLine.EMPTY
                } else {
                    deadLine
                }
            )
        }
    }

    fun updateSelectedLocation(region: Region) {
        intent {
            copy(
                filterRegion = if (uiState.value.filterRegion == region) {
                    Region.EMPTY
                } else {
                    region
                }
            )
        }
    }

    fun updateSelectedPreferDay(clubDay: ClubDay) {
        intent {
            copy(
                filterClubDay = if (uiState.value.filterClubDay == clubDay) {
                    ClubDay.EMPTY
                } else {
                    clubDay
                }
            )
        }
    }

    fun navigateToAfterSearchWithKeywordSearch() {
        if (uiState.value.inputSearch.isNotBlank()) postSideEffect(SearchSideEffect.NavigateAfterSearch)
    }

    fun navigateToAfterSearchWithBottomSheet() {
        if (uiState.value.filterDeadLine != DeadLine.EMPTY ||
            uiState.value.filterRegion != Region.EMPTY ||
            uiState.value.filterClubDay != ClubDay.EMPTY
        ) postSideEffect(SearchSideEffect.NavigateAfterSearch)
    }

    fun navigateToBack() = postSideEffect(SearchSideEffect.NavigateBack)

}