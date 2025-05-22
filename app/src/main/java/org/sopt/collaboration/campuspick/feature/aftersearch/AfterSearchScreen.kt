package org.sopt.collaboration.campuspick.feature.aftersearch

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
import org.sopt.collaboration.campuspick.core.designsystem.component.cardview.ClubSearchCard
import org.sopt.collaboration.campuspick.core.designsystem.component.tabrow.ClubCategoryTabRow
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.extension.addFocusCleaner
import org.sopt.collaboration.campuspick.core.ui.lifecycle.LaunchedEffectWithLifecycle
import org.sopt.collaboration.campuspick.core.viewmodel.ViewModelFactory
import org.sopt.collaboration.campuspick.domain.model.Category
import org.sopt.collaboration.campuspick.domain.model.ClubSearch
import org.sopt.collaboration.campuspick.domain.model.DeadLine
import org.sopt.collaboration.campuspick.domain.model.DeadLine.Companion.labelFromName
import org.sopt.collaboration.campuspick.domain.model.FilteredClub
import org.sopt.collaboration.campuspick.domain.model.PreferDay
import org.sopt.collaboration.campuspick.domain.model.PreferDay.Companion.labelFromName
import org.sopt.collaboration.campuspick.domain.model.Region
import org.sopt.collaboration.campuspick.domain.model.Region.Companion.labelFromName
import org.sopt.collaboration.campuspick.domain.model.SearchType
import org.sopt.collaboration.campuspick.feature.club.DivisionLine
import org.sopt.collaboration.campuspick.feature.search.FilterBottomSheet
import org.sopt.collaboration.campuspick.feature.search.component.FilterSearchBar

@Composable
fun AfterSearchRoute(
    padding: PaddingValues,
    keyword: String?,
    category: String?,
    deadline: String?,
    region: String?,
    clubDay: String?,
    navigateToBack: () -> Unit,
    modifier: Modifier,
    viewModel: AfterSearchViewModel = viewModel(factory = ViewModelFactory())
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                AfterSearchSideEffect.NavigateBack -> navigateToBack()
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
        selectedCategory = Category.indexFromName(uiState.value.currentFilter.category),
        updateSelectedCategory = viewModel::updateSelectedCategory,
        inputSearchValue = uiState.value.currentFilter.keyword.toString(),
        updateInputSearch = viewModel::updateInputSearch,
        filteredClub = uiState.value.filteredClub,
        navigateToBack = viewModel::navigateToBack,
        showFilterBottomSheet = uiState.value.showFilterBottomSheet,
        updateBottomSheetShown = viewModel::updateBottomSheetShown,
        bottomSheetDeadLineSelected = uiState.value.currentFilter.deadline.toString(),
        bottomSheetLocationSelected = uiState.value.currentFilter.region.toString(),
        bottomSheetPreferDaySelected = uiState.value.currentFilter.clubDay.toString(),
        updateSelectedDeadLine = viewModel::updateSelectedDeadLine,
        updateSelectedLocation = viewModel::updateSelectedLocation,
        updateSelectedPreferDay = viewModel::updateSelectedPreferDay,
        modifier = modifier
            .padding(padding)
            .addFocusCleaner(focusManager)
    )
}


@Composable
fun AfterSearchScreen(
    selectedCategory: Int,
    updateSelectedCategory: (Int) -> Unit,
    inputSearchValue: String,
    updateInputSearch: (String) -> Unit,
    filteredClub: List<FilteredClub>,
    navigateToBack: () -> Unit,
    showFilterBottomSheet: Boolean,
    updateBottomSheetShown: (Boolean) -> Unit,
    bottomSheetDeadLineSelected: String,
    bottomSheetLocationSelected: String,
    bottomSheetPreferDaySelected: String,
    updateSelectedDeadLine: (DeadLine) -> Unit,
    updateSelectedLocation: (Region) -> Unit,
    updateSelectedPreferDay: (PreferDay) -> Unit,
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
                SecondClubFilter()
            }
            itemsIndexed(items = filteredClub) { index, club ->
                ClubSearchCard(
                    data = ClubSearch(
                        tags = listOf(club.category, club.region),
                        profile = R.drawable.img_search_advertisement_banner,
                        author = club.name,
                        content = club.postTitle,
                        dDay = club.dDay,
                        viewCount = club.viewCount,
                        commentCount = club.commentCount,
                        poster = R.drawable.img_search_advertisement_banner,
                    ),
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .padding(top = if (index == 0) 0.dp else 15.dp)
                )
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
            bottomSheetLocationSelected = Region.Companion.labelFromName(bottomSheetLocationSelected),
            updateSelectedLocation = updateSelectedLocation,
            bottomSheetPreferDaySelected = PreferDay.Companion.labelFromName(
                bottomSheetPreferDaySelected
            ),
            updateSelectedPreferDay = updateSelectedPreferDay,
        )
    }
}

@Composable
fun SecondClubFilter() {
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