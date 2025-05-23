package org.sopt.collaboration.campuspick.core.designsystem.component.cardview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.preview.DefaultPreview

@Composable
fun CampuspickCard(
    cardBackgroundColor: Color,
    cardImage: Painter,
    imageHeight: Dp,
    cardTitle: String,
    cardContentHorizontalPadding: Dp,
    cardContentVerticalPadding: Dp,
    titleMetaSpacer: Modifier,
    metaInfoContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .width(155.dp)
            .clip(RoundedCornerShape(6.dp)),
        colors = CardDefaults.cardColors(
            contentColor = cardBackgroundColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = cardImage,
                contentDescription = "card image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(imageHeight)
                    .fillMaxWidth()
            )

            CardContent(
                cardTitle = cardTitle,
                cardContentHorizontalPadding = cardContentHorizontalPadding,
                cardContentVerticalPadding = cardContentVerticalPadding,
                titleMetaSpacer = titleMetaSpacer,
                metaInfoContent = metaInfoContent,
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun CardContent(
    cardTitle: String,
    cardContentHorizontalPadding: Dp,
    cardContentVerticalPadding: Dp,
    titleMetaSpacer: Modifier,
    metaInfoContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(CampuspickTheme.colors.Gray5)
            .padding(
                horizontal = cardContentHorizontalPadding,
                vertical = cardContentVerticalPadding
            )
    ) {
        Text(
            text = cardTitle,
            color = CampuspickTheme.colors.Black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = CampuspickTheme.typography.body0
        )

        Spacer(modifier = titleMetaSpacer)

        metaInfoContent()
    }
}

@DefaultPreview
@Composable
private fun CampuspickCardPreview() {
    CampuspickTheme {
        CampuspickCard(
            cardBackgroundColor = CampuspickTheme.colors.Gray5,
            cardImage = painterResource(R.drawable.img_activity_lisolab),
            imageHeight = 155.dp,
            cardTitle = "⭐\uFE0F꿈꾸는 과학 38⭐\uFE0F 신입부원을 상시 모집합니다!",
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
                        text = "D-67",
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
                        text = "2,553",
                        color = CampuspickTheme.colors.Gray2,
                        style = CampuspickTheme.typography.caption3
                    )
                }
            },
            modifier = Modifier
                .height(217.dp)
        )
    }
}
