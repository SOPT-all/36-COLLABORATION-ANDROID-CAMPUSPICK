package org.sopt.collaboration.campuspick.feature.aftersearch

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.component.appbar.CampuspickAppBar
import org.sopt.collaboration.campuspick.core.designsystem.component.bottomsheet.FilterBottomSheet
import org.sopt.collaboration.campuspick.core.designsystem.component.cardview.ClubSearchCard
import org.sopt.collaboration.campuspick.core.designsystem.component.cardview.ClubSearchCardSkeleton
import org.sopt.collaboration.campuspick.core.designsystem.component.tabrow.ClubCategoryTabRow
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.extension.addFocusCleaner
import org.sopt.collaboration.campuspick.core.ui.extension.customClickable
import org.sopt.collaboration.campuspick.core.ui.image.getImageResId
import org.sopt.collaboration.campuspick.core.ui.lifecycle.LaunchedEffectWithLifecycle
import org.sopt.collaboration.campuspick.core.ui.model.ClubDay
import org.sopt.collaboration.campuspick.core.ui.model.ClubDay.Companion.labelFromName
import org.sopt.collaboration.campuspick.core.ui.model.DeadLine
import org.sopt.collaboration.campuspick.core.ui.model.DeadLine.Companion.labelFromName
import org.sopt.collaboration.campuspick.core.ui.model.Region
import org.sopt.collaboration.campuspick.core.ui.model.Region.Companion.labelFromName
import org.sopt.collaboration.campuspick.core.viewmodel.ViewModelFactory
import org.sopt.collaboration.campuspick.domain.model.Category
import org.sopt.collaboration.campuspick.domain.model.ClubSearch
import org.sopt.collaboration.campuspick.domain.model.SearchType
import org.sopt.collaboration.campuspick.feature.club.DivisionLine
import org.sopt.collaboration.campuspick.feature.search.component.FilterSearchBar

@Composable
fun AfterSearchRoute(
    padding: PaddingValues,
    keyword: String?,
    category: String?,
    deadline: String?,
    region: String?,
    clubDay: String?,
    navigateBackToClub: () -> Unit,
    modifier: Modifier,
    viewModel: AfterSearchViewModel = viewModel(factory = ViewModelFactory())
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    BackHandler {
        viewModel.navigateToBack()
    }

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                AfterSearchSideEffect.NavigateBack -> navigateBackToClub()
            }
        }
    }

    LaunchedEffectWithLifecycle(Unit) {
        viewModel.apply {
            updateCurrentFilter(
                SearchType(
                    keyword = keyword ?: "",
                    category = category,
                    deadline = deadline,
                    region = region,
                    clubDay = clubDay
                )
            )
        }
    }

    LaunchedEffectWithLifecycle(uiState.value.currentFilter) {
        viewModel.getFilteredClub()
    }

    AfterSearchScreen(
        clubLoadState = uiState.value.clubLoadState,
        selectedCategory = Category.indexFromName(uiState.value.currentFilter.category),
        updateSelectedCategory = viewModel::updateSelectedCategory,
        inputSearchValue = uiState.value.currentFilter.keyword.toString(),
        updateInputSearch = viewModel::updateInputSearch,
        navigateToBack = viewModel::navigateToBack,
        showFilterBottomSheet = uiState.value.showFilterBottomSheet,
        updateBottomSheetShown = viewModel::updateFilterBottomSheetShown,
        showClubSortBottomSheet = uiState.value.showClubSortBottomSheet,
        updateClubSortBottomSheetShown = viewModel::updateClubSortBottomSheetShown,
        bottomSheetDeadLineSelected = uiState.value.currentFilter.deadline.toString(),
        bottomSheetRegionSelected = uiState.value.currentFilter.region.toString(),
        bottomSheetClubDaySelected = uiState.value.currentFilter.clubDay.toString(),
        updateSelectedDeadLine = viewModel::updateSelectedDeadLine,
        updateSelectedRegion = viewModel::updateSelectedRegion,
        updateSelectedClubDay = viewModel::updateSelectedClubDay,
        modifier = modifier
            .padding(padding)
            .addFocusCleaner(focusManager)
    )
}


@Composable
fun AfterSearchScreen(
    clubLoadState: ClubLoadState,
    selectedCategory: Int,
    updateSelectedCategory: (Int) -> Unit,
    inputSearchValue: String,
    updateInputSearch: (String) -> Unit,
    navigateToBack: () -> Unit,
    showFilterBottomSheet: Boolean,
    updateBottomSheetShown: (Boolean) -> Unit,
    showClubSortBottomSheet: Boolean,
    updateClubSortBottomSheetShown: (Boolean) -> Unit,
    bottomSheetDeadLineSelected: String,
    bottomSheetRegionSelected: String,
    bottomSheetClubDaySelected: String,
    updateSelectedDeadLine: (DeadLine) -> Unit,
    updateSelectedRegion: (Region) -> Unit,
    updateSelectedClubDay: (ClubDay) -> Unit,
    modifier: Modifier,
) {

    val listState = rememberLazyListState()
    var scrollToTop by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        CampuspickAppBar(onBackClick = navigateToBack)
        Spacer(modifier = Modifier.height(23.dp))
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                FilterSearchBar(
                    filterSelected = true,
                    inputSearchValue = inputSearchValue,
                    updateInputSearch = updateInputSearch,
                    updateBottomSheetShown = updateBottomSheetShown,
                    onSearchClick = {},
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .padding(bottom = 6.dp)
                )
            }

            stickyHeader {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CampuspickTheme.colors.White)
                ) {
                    ClubCategoryTabRow(
                        tabTitles = Category.entries.map { it.label }.toImmutableList(),
                        onTabClick = updateSelectedCategory,
                        selectedIndex = selectedCategory,
                        modifier = Modifier.padding(top = 5.dp, bottom = 11.dp)
                    )
                    DivisionLine()
                }
            }
            item {
                Spacer(modifier = Modifier.height(14.dp))
                SecondClubFilter(
                    updateClubSortBottomSheetShown,
                )
            }

            when (clubLoadState) {
                ClubLoadState.Loading -> {
                    items(4) { index ->
                        ClubSearchCardSkeleton(
                            modifier = Modifier
                                .padding(horizontal = 15.dp)
                                .padding(top = if (index == 0) 0.dp else 15.dp)
                        )
                    }
                }

                is ClubLoadState.Success -> {
                    val clubs = clubLoadState.filteredClubs
                    itemsIndexed(clubs) { index, club ->
                        ClubSearchCard(
                            data = ClubSearch(
                                tags = listOf(club.category, club.region),
                                profile = getImageResId(club.image),
                                author = club.name,
                                content = club.postTitle,
                                dDay = club.dDay,
                                viewCount = club.viewCount,
                                commentCount = club.commentCount,
                                poster = getImageResId(club.postImage),
                            ),
                            modifier = Modifier
                                .padding(horizontal = 15.dp)
                                .padding(top = if (index == 0) 0.dp else 15.dp)
                        )
                    }
                }
            }
        }
        FilterBottomSheet(
            showFilterBottomSheet = showFilterBottomSheet,
            onDismiss = updateBottomSheetShown,
            navigateToAfterSearchWithBottomSheet = {},
            bottomSheetDeadLineSelected = DeadLine.Companion.labelFromName(
                bottomSheetDeadLineSelected
            ),
            updateSelectedDeadLine = updateSelectedDeadLine,
            bottomSheetRegionSelected = Region.Companion.labelFromName(bottomSheetRegionSelected),
            updateSelectedRegion = updateSelectedRegion,
            bottomSheetClubDaySelected = ClubDay.Companion.labelFromName(
                bottomSheetClubDaySelected
            ),
            updateSelectedClubDay = updateSelectedClubDay,
        )
        ClubSortBottomSheet(
            showClubSortBottomSheet = showClubSortBottomSheet,
            onDismiss = updateClubSortBottomSheetShown,
            )
    }
}

@Composable
fun SecondClubFilter(
    updateClubSortBottomSheetShown: (Boolean) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, end = 15.dp, bottom = 21.dp)
    ) {
        Text(
            text = "마감된 공고 제외",
            style = CampuspickTheme.typography.caption2,
            color = CampuspickTheme.colors.Black,
            modifier = Modifier.padding(end = 3.dp)
        )
        Icon(
            painter = painterResource(R.drawable.ic_check_in_circle),
            contentDescription = "check",
            tint = Color.Unspecified,
        )
        VerticalDivider(
            modifier = Modifier
                .height(18.dp)
                .padding(horizontal = 10.dp, vertical = 2.dp),
            thickness = 1.dp,
            color = CampuspickTheme.colors.Gray3,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.customClickable(
                rippleEnabled = false,
                onClick = { updateClubSortBottomSheetShown(true) },
            )
        ) {
            Text(
                text = "최신순",
                style = CampuspickTheme.typography.caption2,
                color = CampuspickTheme.colors.Black,
                modifier = Modifier.padding(end = 3.dp)
            )
            Icon(
                painter = painterResource(R.drawable.ic_under_arrow),
                contentDescription = "under arrow",
                tint = Color.Unspecified,
            )
        }
    }
}