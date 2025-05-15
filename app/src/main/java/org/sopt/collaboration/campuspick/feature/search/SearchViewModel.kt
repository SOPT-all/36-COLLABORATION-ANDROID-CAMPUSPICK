package org.sopt.collaboration.campuspick.feature.search

import org.sopt.collaboration.campuspick.core.ui.base.BaseViewModel
import org.sopt.collaboration.campuspick.domain.model.DeadLine
import org.sopt.collaboration.campuspick.domain.model.Location
import org.sopt.collaboration.campuspick.domain.model.PreferDay

class SearchViewModel() : BaseViewModel<SearchState, SearchSideEffect>(SearchState()) {

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

    fun updateSelectedLocation(location: Location) {
        intent {
            copy(
                filterLocation = if (uiState.value.filterLocation == location) {
                    Location.EMPTY
                } else {
                    location
                }
            )
        }
    }

    fun updateSelectedPreferDay(preferDay: PreferDay) {
        intent {
            copy(
                filterPreferDay = if (uiState.value.filterPreferDay == preferDay) {
                    PreferDay.EMPTY
                } else {
                    preferDay
                }
            )
        }
    }
}