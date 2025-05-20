package org.sopt.collaboration.campuspick.feature.aftersearch

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.collaboration.campuspick.core.ui.base.BaseViewModel
import org.sopt.collaboration.campuspick.domain.model.Category
import org.sopt.collaboration.campuspick.domain.model.SearchType
import org.sopt.collaboration.campuspick.domain.repository.CampusPickRepository

class AfterSearchViewModel(private val campusPickRepository: CampusPickRepository) :
    BaseViewModel<AfterSearchState, AfterSearchSideEffect>(AfterSearchState()) {

    fun updateCurrentFilter(
        searchType: SearchType = SearchType(
            keyword = uiState.value.currentFilter.keyword,
            category = uiState.value.currentFilter.category,
            deadline = uiState.value.currentFilter.deadline,
            region = uiState.value.currentFilter.region,
            clubDay = uiState.value.currentFilter.clubDay
        )
    ) {
        intent {
            copy(
                currentFilter = searchType
            )
        }
    }

    fun getFilteredClub() {
        viewModelScope.launch {
            delay(300L)
            campusPickRepository.getSearchClubs(
                keyword = uiState.value.currentFilter.keyword,
                category = uiState.value.currentFilter.category,
                deadlineType = uiState.value.currentFilter.deadline,
                region = uiState.value.currentFilter.region,
                clubDay = uiState.value.currentFilter.clubDay
            ).onSuccess {
                intent {
                    copy(
                        filteredClub = it
                    )
                }
            }
        }
    }

    fun updateSelectedCategory(categoryIndex: Int) {
        val categoryName =
            if (categoryIndex == 0) null else Category.entries.getOrNull(categoryIndex)?.name
        intent {
            copy(
                currentFilter = currentFilter.copy(category = categoryName)
            )
        }
    }

    fun updateInputSearch(inputSearch: String) {
        intent {
            copy(
                currentFilter = currentFilter.copy(keyword = inputSearch)
            )
        }
    }

    fun updateBottomSheetShown(shown: Boolean) {
        intent {
            copy(showFilterBottomSheet = shown)
        }
    }

    fun navigateToBack() = postSideEffect(AfterSearchSideEffect.NavigateBack)
}