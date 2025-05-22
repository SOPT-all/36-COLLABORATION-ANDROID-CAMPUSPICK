package org.sopt.collaboration.campuspick.feature.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.component.bottomsheet.FilterBottomSheet
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.extension.addFocusCleaner
import org.sopt.collaboration.campuspick.core.ui.lifecycle.LaunchedEffectWithLifecycle
import org.sopt.collaboration.campuspick.core.ui.model.DeadLine
import org.sopt.collaboration.campuspick.core.ui.model.ClubDay
import org.sopt.collaboration.campuspick.core.ui.model.Region
import org.sopt.collaboration.campuspick.domain.model.SearchType
import org.sopt.collaboration.campuspick.feature.search.SearchSideEffect.NavigateAfterSearch
import org.sopt.collaboration.campuspick.feature.search.component.RecentSearchKeyword
import org.sopt.collaboration.campuspick.feature.search.component.SearchHeader
import org.sopt.collaboration.campuspick.feature.search.component.SearchKeyword

@Composable
fun SearchRoute(
    padding: PaddingValues,
    navigateToAfterSearch: (SearchType) -> Unit,
    navigateToBack: () -> Unit,
    modifier: Modifier,
    viewModel: SearchViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                NavigateAfterSearch -> navigateToAfterSearch(
                    SearchType(
                        keyword = if (uiState.value.inputSearch.isBlank()) null else uiState.value.inputSearch,
                        category = null,
                        deadline = uiState.value.filterDeadLine.replaceDeadLine,
                        region = uiState.value.filterRegion.replaceRegion,
                        clubDay = uiState.value.filterClubDay.replaceDay
                    )
                )

                SearchSideEffect.NavigateBack -> navigateToBack()
            }
        }
    }

    SearchScreen(
        inputSearchValue = uiState.value.inputSearch,
        updateInputSearch = viewModel::updateInputSearch,
        showFilterBottomSheet = uiState.value.showFilterBottomSheet,
        updateBottomSheetShown = viewModel::updateBottomSheetShown,
        bottomSheetDeadLineSelected = uiState.value.filterDeadLine.label,
        updateSelectedDeadLine = viewModel::updateSelectedDeadLine,
        bottomSheetRegionSelected = uiState.value.filterRegion.label,
        updateSelectedRegion = viewModel::updateSelectedLocation,
        bottomSheetClubDaySelected = uiState.value.filterClubDay.label,
        updateSelectedClubDay = viewModel::updateSelectedPreferDay,
        navigateToAfterSearchWithKeywordSearch = viewModel::navigateToAfterSearchWithKeywordSearch,
        navigateToAfterSearchWithBottomSheet = viewModel::navigateToAfterSearchWithBottomSheet,
        navigateToBack = viewModel::navigateToBack,
        modifier = modifier
            .addFocusCleaner(focusManager)
            .padding(padding)
    )
}

@Composable
fun SearchScreen(
    inputSearchValue: String,
    updateInputSearch: (String) -> Unit,
    showFilterBottomSheet: Boolean,
    updateBottomSheetShown: (Boolean) -> Unit,
    bottomSheetDeadLineSelected: String,
    updateSelectedDeadLine: (DeadLine) -> Unit,
    bottomSheetRegionSelected: String,
    updateSelectedRegion: (Region) -> Unit,
    bottomSheetClubDaySelected: String,
    updateSelectedClubDay: (ClubDay) -> Unit,
    navigateToAfterSearchWithKeywordSearch: () -> Unit,
    navigateToAfterSearchWithBottomSheet: () -> Unit,
    navigateToBack: () -> Unit,
    modifier: Modifier,
) {
    Column(modifier = modifier) {
        SearchHeader(
            inputSearchValue = inputSearchValue,
            updateInputSearch = updateInputSearch,
            updateBottomSheetShown = updateBottomSheetShown,
            onSearchClick = navigateToAfterSearchWithKeywordSearch,
            navigateToBack = navigateToBack,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
        HorizontalDivider(
            thickness = 2.dp,
            color = CampuspickTheme.colors.Gray4,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        SearchKeyword(
            title = "최근 검색어",
            keywords = listOf("디자인", "IT", "개발", "밴드"),
            modifier = Modifier.padding(start = 15.dp, bottom = 25.dp),
            keywordType = {
                RecentSearchKeyword(
                    buttonText = it,
                    textColor = CampuspickTheme.colors.Gray1,
                    outlineColor = CampuspickTheme.colors.Gray4,
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.ic_delete),
                            contentDescription = "delete",
                            tint = Color.Unspecified,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                )
            }
        )
        SearchKeyword(
            title = "인기 검색어",
            keywords = listOf("전시회", "댄스", "밴드", "영상 제작", "영화", "야구", "여행 동아리"),
            modifier = Modifier.padding(start = 15.dp, bottom = 25.dp),
            keywordType = {
                RecentSearchKeyword(
                    buttonText = it,
                    textColor = CampuspickTheme.colors.Blue,
                    outlineColor = CampuspickTheme.colors.Blue
                )
            }
        )
        Image(
            painter = painterResource(R.drawable.img_search_advertisement_banner),
            contentDescription = "advertisement",
            modifier = Modifier.padding(horizontal = 15.dp)
        )

        FilterBottomSheet(
            showFilterBottomSheet = showFilterBottomSheet,
            onDismiss = updateBottomSheetShown,
            navigateToAfterSearchWithBottomSheet = navigateToAfterSearchWithBottomSheet,
            bottomSheetDeadLineSelected = bottomSheetDeadLineSelected,
            updateSelectedDeadLine = updateSelectedDeadLine,
            bottomSheetRegionSelected = bottomSheetRegionSelected,
            updateSelectedRegion = updateSelectedRegion,
            bottomSheetClubDaySelected = bottomSheetClubDaySelected,
            updateSelectedClubDay = updateSelectedClubDay,
        )
    }
}