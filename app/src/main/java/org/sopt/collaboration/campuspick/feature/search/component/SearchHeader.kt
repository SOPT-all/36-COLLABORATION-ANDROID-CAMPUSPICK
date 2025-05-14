package org.sopt.collaboration.campuspick.feature.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.component.textfield.CampuspickSearchBar
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.extension.customClickable

@Composable
fun SearchHeader(
    inputSearchValue: String,
    updateInputSearch: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 23.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HeaderBackButton {
                    // TODO: 뒤로가기
                }
                Text(
                    text = "내 활동",
                    style = CampuspickTheme.typography.heading1,
                    color = CampuspickTheme.colors.Blue
                )
            }
            Text(
                text = "동아리",
                style = CampuspickTheme.typography.heading1,
                color = CampuspickTheme.colors.Blue,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CampuspickSearchBar(
                placeholder = "찾으시는 동아리가 있나요?",
                value = inputSearchValue,
                onValueChange = updateInputSearch,
                onSearchClick = null,
                modifier = Modifier.weight(1f),
                usedOnlyNavigation = false
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = CampuspickTheme.colors.Gray4,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_filter),
                    contentDescription = "filter",
                    tint = Color.Unspecified,
                )
            }
        }
    }

}

@Composable
fun HeaderBackButton(
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.customClickable(
            rippleEnabled = false,
            onClick = onClick
        )
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_topbar_back_arrow),
            contentDescription = "back",
            tint = Color.Unspecified,
            modifier = Modifier.padding(end = 7.dp)
        )
        Text(
            text = "뒤로",
            style = CampuspickTheme.typography.heading1,
            color = CampuspickTheme.colors.Blue
        )
    }
}