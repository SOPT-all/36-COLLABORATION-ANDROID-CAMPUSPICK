package org.sopt.collaboration.campuspick.feature.club.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.component.cardview.CampuspickCard
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.image.getImageResId
import org.sopt.collaboration.campuspick.domain.model.ClubRecruitment

@Composable
fun ClubRecruitmentCard(
    data: ClubRecruitment,
    modifier: Modifier = Modifier,
) {
    CampuspickCard(
        cardBackgroundColor = CampuspickTheme.colors.Gray5,
        cardImage = painterResource(getImageResId(data.image)),
        imageHeight = 155.dp,
        cardTitle = data.title,
        cardContentHorizontalPadding = 6.dp,
        cardContentVerticalPadding = 4.dp,
        titleMetaSpacer = Modifier.height(6.dp),
        metaInfoContent = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "D-${data.dday}",
                    color = CampuspickTheme.colors.Gray2,
                    style = CampuspickTheme.typography.caption3
                )

                Spacer(modifier = Modifier.width(4.dp))

                Image(
                    modifier = Modifier
                        .size(12.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_post_shown),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = data.viewCount.toString(),
                    color = CampuspickTheme.colors.Gray2,
                    style = CampuspickTheme.typography.caption3
                )
            }
        },
        modifier = modifier
            .height(217.dp)
    )
}