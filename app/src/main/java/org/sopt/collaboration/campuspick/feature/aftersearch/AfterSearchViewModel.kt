package org.sopt.collaboration.campuspick.feature.aftersearch

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import org.sopt.collaboration.campuspick.core.ui.base.BaseViewModel
import org.sopt.collaboration.campuspick.core.ui.model.ClubDay
import org.sopt.collaboration.campuspick.core.ui.model.DeadLine
import org.sopt.collaboration.campuspick.core.ui.model.Region
import org.sopt.collaboration.campuspick.domain.model.Category
import org.sopt.collaboration.campuspick.domain.model.SearchType
import org.sopt.collaboration.campuspick.domain.repository.CampusPickRepository

@OptIn(FlowPreview::class)
class AfterSearchViewModel(private val campusPickRepository: CampusPickRepository) :
    BaseViewModel<AfterSearchState, AfterSearchSideEffect>(AfterSearchState()) {

    private val _searchFlow = MutableSharedFlow<Unit>()

    init {
        viewModelScope.launch {
            _searchFlow
                .debounce(300L)
                .collect {
                    intent {
                        copy(
                            clubLoadState = ClubLoadState.Loading
                        )
                    }
                    provideSkeletonTest {
                        campusPickRepository.getSearchClubs(
                            keyword = uiState.value.currentFilter.keyword,
                            category = uiState.value.currentFilter.category,
                            deadlineType = uiState.value.currentFilter.deadline,
                            region = uiState.value.currentFilter.region,
                            clubDay = uiState.value.currentFilter.clubDay
                        )
                    }.onSuccess {
                        intent {
                            copy(
                                clubLoadState = ClubLoadState.Success(it)
                            )
                        }
                    }.onFailure {
                        intent {
                            copy(
                                clubLoadState = ClubLoadState.Loading
                            )
                        }
                    }
                }
        }
    }

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
            _searchFlow.emit(Unit)
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

    fun updateSelectedDeadLine(deadLine: DeadLine) {
        intent {
            copy(
                currentFilter = currentFilter.copy(
                    deadline =
                        if (uiState.value.currentFilter.deadline == deadLine.name)
                            DeadLine.EMPTY.replaceDeadLine
                        else deadLine.replaceDeadLine
                )
            )
        }
    }

    fun updateSelectedRegion(region: Region) {
        intent {
            copy(
                currentFilter = currentFilter.copy(
                    region =
                        if (uiState.value.currentFilter.region == region.name)
                            Region.EMPTY.replaceRegion
                        else region.replaceRegion
                )
            )
        }
    }

    fun updateSelectedClubDay(clubDay: ClubDay) {
        intent {
            copy(
                currentFilter = currentFilter.copy(
                    clubDay =
                        if (uiState.value.currentFilter.clubDay == clubDay.name)
                            ClubDay.EMPTY.replaceDay
                        else
                            clubDay.replaceDay
                )
            )
        }
    }

    fun navigateToBack() = postSideEffect(AfterSearchSideEffect.NavigateBack)

    suspend fun <T> provideSkeletonTest(
        minTimeMillis: Long = 500L,
        block: suspend () -> T
    ): T {
        val startTime = System.currentTimeMillis()
        val result = block()
        val elapsed = System.currentTimeMillis() - startTime
        if (elapsed < minTimeMillis) {
            delay(minTimeMillis - elapsed)
        }
        return result
    }
}