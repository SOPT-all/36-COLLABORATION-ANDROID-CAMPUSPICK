package org.sopt.collaboration.campuspick.feature.search

import org.sopt.collaboration.campuspick.core.ui.base.BaseViewModel
import org.sopt.collaboration.campuspick.domain.model.DeadLine
import org.sopt.collaboration.campuspick.domain.model.PreferDay
import org.sopt.collaboration.campuspick.domain.model.Region
import org.sopt.collaboration.campuspick.domain.repository.CampusPickRepository

class SearchViewModel(
    private val campusPickRepository: CampusPickRepository
) : BaseViewModel<SearchState, SearchSideEffect>(SearchState()) {

//    init {
//        viewModelScope.launch {
//            campusPickRepository.getSearchClubs(
//                title = null,
//                category = null,
//                deadlineType = null,
//                region = null,
//                clubDay = null
//            ).onSuccess {
//                Log.d("asdasdasd", it.toString())
//            }.onFailure {
//                Log.d("asdasdasd", it.toString())
//            }
//        }
//    }

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

    fun navigateToAfterSearchWithKeywordSearch() {
        if (uiState.value.inputSearch.isNotBlank()) postSideEffect(SearchSideEffect.NavigateAfterSearch)
    }

    fun navigateToAfterSearchWithBottomSheet() {
        if (uiState.value.filterDeadLine != DeadLine.EMPTY ||
            uiState.value.filterRegion != Region.EMPTY ||
            uiState.value.filterPreferDay != PreferDay.EMPTY
        ) postSideEffect(SearchSideEffect.NavigateAfterSearch)
    }
}