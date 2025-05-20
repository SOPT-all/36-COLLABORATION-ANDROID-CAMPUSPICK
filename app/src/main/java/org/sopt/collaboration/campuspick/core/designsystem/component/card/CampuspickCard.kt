package org.sopt.collaboration.campuspick.core.designsystem.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme

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
            .height(172.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(cardBackgroundColor)
    ) {
        Image(
            painter = cardImage,
            contentDescription = "card image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(imageHeight)
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