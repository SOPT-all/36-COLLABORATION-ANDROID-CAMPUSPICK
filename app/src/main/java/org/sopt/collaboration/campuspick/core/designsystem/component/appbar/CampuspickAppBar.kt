package org.sopt.collaboration.campuspick.core.designsystem.component.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.feature.search.component.HeaderBackButton

@Composable
fun CampuspickAppBar(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 15.dp)
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
                onBackClick
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
}