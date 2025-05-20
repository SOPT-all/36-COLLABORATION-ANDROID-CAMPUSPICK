package org.sopt.collaboration.campuspick.feature.aftersearch

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.collaboration.campuspick.core.ui.base.BaseViewModel
import org.sopt.collaboration.campuspick.domain.repository.CampusPickRepository

class AfterSearchViewModel(private val campusPickRepository: CampusPickRepository) :
    BaseViewModel<AfterSearchState, AfterSearchSideEffect>(AfterSearchState()) {

    fun updateCurrentFilter(
        keyword: String = uiState.value.currentKeyword,
        category: String = uiState.value.currentCategory,
        deadLine: String = uiState.value.currentDeadLine,
        region: String = uiState.value.currentRegion,
        clubDay: String = uiState.value.currentClubDay
    ) {
        intent {
            copy(
                currentKeyword = keyword,
                currentCategory = category,
                currentDeadLine = deadLine,
                currentRegion = region,
                currentClubDay = clubDay
            )
        }
    }

    fun getFilteredClub(
        keyword: String?,
        category: String?,
        deadlineType: String?,
        region: String?,
        clubDay: String?,
    ) {
        viewModelScope.launch {
            campusPickRepository.getSearchClubs(
                keyword = keyword,
                category = category,
                deadlineType = deadlineType,
                region = region,
                clubDay = clubDay
            ).onSuccess {
                intent {
                    copy(
                        filteredClub = it
                    )
                }
            }
        }
    }
}