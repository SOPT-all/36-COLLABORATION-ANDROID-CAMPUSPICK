package org.sopt.collaboration.campuspick.feature.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.component.button.CampuspickBasicButton
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.extension.addFocusCleaner
import org.sopt.collaboration.campuspick.feature.search.component.SearchHeader
import org.sopt.collaboration.campuspick.feature.search.component.SearchKeyword

@Composable
fun SearchRoute(
    padding: PaddingValues,
    modifier: Modifier,
    viewModel: SearchViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current



    SearchScreen(
        inputSearchValue = uiState.value.inputSearch,
        updateInputSearch = viewModel::updateInputSearch,
        modifier = modifier
            .addFocusCleaner(focusManager)
            .padding(padding)
    )
}

@Composable
fun SearchScreen(
    inputSearchValue: String,
    updateInputSearch: (String) -> Unit,
    modifier: Modifier,
) {
    Column(modifier = modifier) {
        SearchHeader(
            inputSearchValue = inputSearchValue,
            updateInputSearch = updateInputSearch,
            modifier = modifier.padding(horizontal = 15.dp)
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
    }
}

@Composable
fun RecentSearchKeyword(
    buttonText: String,
    textColor: Color,
    outlineColor: Color,
    content: (@Composable RowScope.() -> Unit)? = null
) {
    Row {
        CampuspickBasicButton(
            buttonText = buttonText,
            onClick = { },
            paddingValues = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
            textStyle = CampuspickTheme.typography.caption2,
            textColor = textColor,
            backgroundColor = CampuspickTheme.colors.White,
            outlineColor = outlineColor,
            modifier = Modifier.padding(end = 11.dp),
            content = content
        )
    }
}