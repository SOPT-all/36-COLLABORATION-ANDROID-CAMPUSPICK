package org.sopt.collaboration.campuspick.feature.aftersearch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.collections.immutable.toImmutableList
import org.sopt.collaboration.campuspick.core.designsystem.component.appbar.CampuspickAppBar
import org.sopt.collaboration.campuspick.core.designsystem.component.tabrow.ClubCategoryTabRow
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.extension.addFocusCleaner
import org.sopt.collaboration.campuspick.core.ui.lifecycle.LaunchedEffectWithLifecycle
import org.sopt.collaboration.campuspick.core.viewmodel.ViewModelFactory
import org.sopt.collaboration.campuspick.domain.model.Category
import org.sopt.collaboration.campuspick.domain.model.FilteredClub
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
    navigateToBack: () -> Unit,
    modifier: Modifier,
    viewModel: AfterSearchViewModel = viewModel(factory = ViewModelFactory())
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    LaunchedEffectWithLifecycle {
        viewModel.updateCurrentFilter(
            SearchType(
                keyword = keyword,
                category = category,
                deadline = deadline,
                region = region,
                clubDay = clubDay
            )
        )
    }

    LaunchedEffectWithLifecycle(uiState.value.currentFilter) {
        viewModel.getFilteredClub()
    }

    AfterSearchScreen(
        inputSearchValue = uiState.value.inputSearch,
        updateInputSearch = viewModel::updateInputSearch,
        updateBottomSheetShown = viewModel::updateBottomSheetShown,
        filteredClub = uiState.value.filteredClub,
        navigateToBack = viewModel::navigateToBack,
        modifier = modifier
            .padding(padding)
            .addFocusCleaner(focusManager)
    )
}

@Composable
fun AfterSearchScreen(
    inputSearchValue: String,
    updateInputSearch: (String) -> Unit,
    updateBottomSheetShown: (Boolean) -> Unit,
    filteredClub: List<FilteredClub>,
    navigateToBack: () -> Unit,
    modifier: Modifier,
) {

    val listState = rememberLazyListState()
    var scrollToTop by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        CampuspickAppBar(
            onBackClick = {},
        )
        Spacer(modifier = Modifier.height(23.dp))
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                FilterSearchBar(
                    inputSearchValue = inputSearchValue,
                    updateInputSearch = updateInputSearch,
                    updateBottomSheetShown = updateBottomSheetShown,
                    onSearchClick = {},
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .padding(bottom = 11.dp)
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
                        onTabClick = {},
                        selectedIndex = 1,
                        modifier = Modifier.padding(bottom = 11.dp)
                    )
                    DivisionLine()
                }
            }

            itemsIndexed(items = filteredClub) { index, club ->
                Spacer(modifier = Modifier.height(14.dp))
                Text(text = "${index + 1}. ${club.name}", Modifier.fillMaxWidth())
                Text(text = "${index + 1}. ${club.name}", Modifier.fillMaxWidth())
                Text(text = "${index + 1}. ${club.name}", Modifier.fillMaxWidth())
                Text(text = "${index + 1}. ${club.name}", Modifier.fillMaxWidth())
                Text(text = "${index + 1}. ${club.name}", Modifier.fillMaxWidth())
                Text(text = "${index + 1}. ${club.name}", Modifier.fillMaxWidth())
            }
        }
    }
}