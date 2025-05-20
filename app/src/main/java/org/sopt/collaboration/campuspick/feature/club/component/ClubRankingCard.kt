package org.sopt.collaboration.campuspick.feature.club.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.domain.model.ClubImage
import org.sopt.collaboration.campuspick.domain.model.ClubRanking

@Composable
fun ClubRankingCard(
    index: Int,
    data: ClubRanking,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = (index + 1).toString(),
            style = CampuspickTheme.typography.heading2,
            color = CampuspickTheme.colors.Blue
        )
        Spacer(Modifier.width(20.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = data.clubName,
                style = CampuspickTheme.typography.body0,
                color = CampuspickTheme.colors.Black
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = data.clubIntroduce,
                style = CampuspickTheme.typography.caption4,
                color = CampuspickTheme.colors.Gray2
            )
        }
        Spacer(Modifier.width(20.dp))
        Image(
            painter = painterResource(ClubImage.getImageId(data.clubImage)),
            contentDescription = null,
            modifier = Modifier
                .size(57.dp)
                .clip(CircleShape)
        )
    }
}