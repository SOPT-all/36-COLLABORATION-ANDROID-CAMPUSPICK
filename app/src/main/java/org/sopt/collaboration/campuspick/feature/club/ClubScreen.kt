package org.sopt.collaboration.campuspick.feature.club

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.collections.immutable.toImmutableList
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.component.appbar.CampuspickAppBar
import org.sopt.collaboration.campuspick.core.designsystem.component.cardview.ClubSearchCard
import org.sopt.collaboration.campuspick.core.designsystem.component.tabrow.ClubCategoryTabRow
import org.sopt.collaboration.campuspick.core.designsystem.component.textfield.CampuspickSearchBar
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.preview.DefaultPreview
import org.sopt.collaboration.campuspick.core.viewmodel.ViewModelFactory
import org.sopt.collaboration.campuspick.domain.model.Category
import org.sopt.collaboration.campuspick.domain.model.ClubRanking
import org.sopt.collaboration.campuspick.domain.model.ClubSearch
import org.sopt.collaboration.campuspick.feature.club.component.ClubRankingCard

@Composable
fun ClubRoute(
    padding: PaddingValues,
    modifier: Modifier,
    navigateBack: () -> Unit,
    navigateToSearch: () -> Unit,
    viewModel: ClubViewModel = viewModel(factory = ViewModelFactory())
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val clubRanking = viewModel.clubRanking.collectAsStateWithLifecycle()

    ClubScreen(
        navigateBack = navigateBack,
        navigateToSearch = navigateToSearch,
        inputSearchValue = uiState.value.inputSearch,
        updateInputSearch = viewModel::updateInputSearch,
        selectedCategoryIndex = uiState.value.selectedTabIndex,
        updateSelectedCategory = viewModel::selectCategory,
        clubRankingList = clubRanking.value,
        clubSearchList = viewModel.clubSearchDummy,
        modifier = modifier
            .padding(padding)
    )
}

@Composable
fun ClubScreen(
    navigateBack: () -> Unit,
    navigateToSearch: () -> Unit,
    inputSearchValue: String,
    updateInputSearch: (String) -> Unit,
    selectedCategoryIndex: Int,
    updateSelectedCategory: (Int) -> Unit,
    clubRankingList: List<ClubRanking>,
    clubSearchList: List<ClubSearch>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.fillMaxSize()
    ) {
        //TODO: 스크롤 영역 지정
        item {
            Spacer(Modifier.height(23.dp))
            CampuspickAppBar(
                onBackClick = navigateBack
            )
            Spacer(Modifier.height(23.dp))
            CampuspickSearchBar(
                placeholder = "찾으시는 동아리가 있나요?",
                value = inputSearchValue,
                onValueChange = updateInputSearch,
                usedOnlyNavigation = true,
                onSearchClick = navigateToSearch,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
        }
        item {
            ClubCategoryTabRow(
                tabTitles = Category.entries.map { it.label }.toImmutableList(),
                onTabClick = updateSelectedCategory,
                selectedIndex = selectedCategoryIndex,
                modifier = Modifier.padding(vertical = 22.dp)
            )
            DivisionLine()
        }
        item { // 인기 모집 공고
            PopularRecruitmentSection()
        }
        item { // 동아리 랭킹
            ClubRankingSection(clubRankingList)
        }
        item { // 동아리 탐색
            ClubSearchSection(clubSearchList)
        }
    }
}

@Composable
fun PopularRecruitmentSection() {
    Spacer(Modifier.height(23.dp))
    Text(
        text = "인기 모집 공고",
        style = CampuspickTheme.typography.heading4,
        color = CampuspickTheme.colors.Black,
        modifier = Modifier.padding(start = 15.dp)
    )
    Spacer(Modifier.height(20.dp))
    //TODO: 인기 모집 공고 LazyRow 추가
    DivisionLine()
}

@Composable
fun ClubRankingSection(clubRankings: List<ClubRanking>) {
    Spacer(Modifier.height(12.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "동아리 랭킹",
            style = CampuspickTheme.typography.heading4,
            color = CampuspickTheme.colors.Black
        )
        Text(
            text = "더보기",
            style = CampuspickTheme.typography.body1,
            color = CampuspickTheme.colors.Gray2
        )
    }
    Spacer(Modifier.height(20.dp))
    clubRankings.forEachIndexed { index, clubData ->
        Box(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(top = if (index == 0) 0.dp else 15.dp)
        ) {
            ClubRankingCard(
                index = index,
                data = clubData,
            )
        }
    }
    Spacer(Modifier.height(13.dp))
    DivisionLine()
}

@Composable
fun ClubSearchSection(clubSearchs: List<ClubSearch>) {
    Spacer(Modifier.height(15.dp))
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Text(
            text = "동아리 탐색",
            style = CampuspickTheme.typography.heading4,
            color = CampuspickTheme.colors.Black
        )
        Spacer(Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "마감된 공고 제외",
                style = CampuspickTheme.typography.caption4,
                color = CampuspickTheme.colors.Black
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_check_in_circle),
                tint = CampuspickTheme.colors.Gray2,
                contentDescription = null
            )
        }
        Spacer(
            Modifier
                .height(12.dp)
                .width(1.dp)
                .background(CampuspickTheme.colors.Gray3)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "지역",
                style = CampuspickTheme.typography.caption4,
                color = CampuspickTheme.colors.Black
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_under_arrow),
                tint = CampuspickTheme.colors.Gray2,
                contentDescription = null
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "최신순",
                style = CampuspickTheme.typography.caption4,
                color = CampuspickTheme.colors.Black
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_under_arrow),
                tint = CampuspickTheme.colors.Gray2,
                contentDescription = null
            )
        }
    }
    Spacer(Modifier.height(23.dp))
    clubSearchs.forEachIndexed { index, data ->
        ClubSearchCard(
            data = data,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(top = if (index == 0) 0.dp else 15.dp)
        )
    }
}

@Composable
fun DivisionLine(
    height: Dp = 7.dp,
    modifier: Modifier = Modifier
) {
    Spacer(modifier
        .height(height)
        .background(CampuspickTheme.colors.Gray4)
        .fillMaxWidth()
    )
}

@DefaultPreview
@Composable
private fun ClubScreenPreview() {
    val viewModel: ClubViewModel = viewModel()
    ClubScreen(
        navigateBack = { },
        navigateToSearch = { },
        inputSearchValue = "",
        updateInputSearch = {},
        selectedCategoryIndex = 0,
        updateSelectedCategory = { },
        clubRankingList = emptyList(),
        clubSearchList = viewModel.clubSearchDummy
    )
}