package org.sopt.collaboration.campuspick.core.designsystem.component.cardview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.component.button.TagLabel
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.preview.DefaultPreview
import org.sopt.collaboration.campuspick.domain.model.ClubSearch
import org.sopt.collaboration.campuspick.feature.club.DivisionLine

@Composable
fun ClubSearchCard(
    data: ClubSearch,
    modifier: Modifier = Modifier,
) {
    Column {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(data.tags) { tag ->
                        TagLabel(tag)
                    }
                }
                Spacer(Modifier.height(13.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(data.profile),
                        contentDescription = null,
                        modifier = Modifier
                            .size(29.dp)
                            .clip(CircleShape)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = data.author,
                        style = CampuspickTheme.typography.body2,
                        color = CampuspickTheme.colors.Black
                    )
                }
                Spacer(Modifier.height(7.dp))
                Text(
                    text = data.content,
                    style = CampuspickTheme.typography.body2,
                    color = CampuspickTheme.colors.Gray1
                )
                Spacer(Modifier.height(15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "D-${data.dDay}",
                        style = CampuspickTheme.typography.caption1,
                        color = CampuspickTheme.colors.Blue
                    )
                    Spacer(Modifier.width(9.dp))
                    Row {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_post_shown),
                            tint = CampuspickTheme.colors.Gray2,
                            modifier = Modifier.size(12.dp),
                            contentDescription = null
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = data.viewCount.toString(),
                            style = CampuspickTheme.typography.caption3,
                            color = CampuspickTheme.colors.Gray2
                        )
                    }
                    Spacer(Modifier.width(5.dp))
                    Row {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_post_comment),
                            tint = CampuspickTheme.colors.Gray2,
                            modifier = Modifier.size(12.dp),
                            contentDescription = null
                        )
                        Spacer(Modifier.height(2.dp))
                        Text(
                            text = data.commentCount.toString(),
                            style = CampuspickTheme.typography.caption3,
                            color = CampuspickTheme.colors.Gray2
                        )
                    }
                }
            }
            Spacer(Modifier.width(8.dp))
            Image(
                painter = painterResource(data.poster),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(77.dp)
                    .height(109.dp)
            )
        }
        Spacer(Modifier.height(22.dp))
        DivisionLine(height = 2.dp)
    }
}

@DefaultPreview
@Composable
private fun ClubSearchCardPreview() {
    ClubSearchCard(
        ClubSearch(
            tags = listOf("기타", "수도권"),
            profile = R.drawable.ic_launcher_background,
            author = "콕티에르",
            content = "\uD83C\uDF7B라이프 스타일 주류 커뮤니티 [COQUETIER] 21.5기 모집\uD83C\uDF7B",
            dDay = 7,
            viewCount = 144,
            commentCount = 187,
            poster = R.drawable.ic_launcher_background
        ),
    )
}