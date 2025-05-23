package org.sopt.collaboration.campuspick.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.extension.customClickable
import org.sopt.collaboration.campuspick.core.ui.preview.DefaultPreview

@Composable
fun HomeHeader(
    alarmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 28.dp, bottom = 15.dp, start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = "캠퍼스픽",
            style = CampuspickTheme.typography.heading1,
            color = CampuspickTheme.colors.Blue
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_alarm),
            contentDescription = "alarm",
            tint = CampuspickTheme.colors.Gray2,
            modifier = Modifier.customClickable(
                rippleEnabled = false,
                onClick = alarmClick
            )
        )
    }
}

@DefaultPreview
@Composable
private fun HomeHeaderPreview() {
    HomeHeader(alarmClick = {})
}