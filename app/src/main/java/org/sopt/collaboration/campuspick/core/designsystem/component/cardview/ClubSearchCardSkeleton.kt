package org.sopt.collaboration.campuspick.core.designsystem.component.cardview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.extension.shimmer
import org.sopt.collaboration.campuspick.feature.club.DivisionLine

@Composable
fun ClubSearchCardSkeleton(modifier: Modifier = Modifier) {
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
                    items(3) {
                        Box(
                            modifier = Modifier
                                .height(24.dp)
                                .width(40.dp)
                                .skeletonUiBox(
                                    clipShape = CircleShape,
                                    paddingValues = PaddingValues(
                                        vertical = 3.dp
                                    )
                                )
                        )
                    }
                }
                Spacer(Modifier.height(13.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(29.dp)
                            .skeletonUiBox(clipShape = CircleShape)
                    )
                    Spacer(Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .width(70.dp)
                            .skeletonUiBox(clipShape = CircleShape)
                    )
                }
                Spacer(Modifier.height(7.dp))
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth(0.8f)
                        .skeletonUiBox(clipShape = CircleShape)
                )
                Spacer(Modifier.height(15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .width(30.dp)
                            .skeletonUiBox(clipShape = CircleShape)
                    )
                    Spacer(Modifier.width(5.dp))
                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .width(30.dp)
                            .skeletonUiBox(clipShape = CircleShape)
                    )
                    Spacer(Modifier.width(5.dp))
                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .width(30.dp)
                            .skeletonUiBox(clipShape = CircleShape)
                    )
                }
            }
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .width(77.dp)
                    .height(108.dp)
                    .skeletonUiBox()
            )
        }
        Spacer(Modifier.height(20.dp))
        DivisionLine(height = 2.dp)
    }
}

@Composable
private fun Modifier.skeletonUiBox(
    clipShape: Shape = RoundedCornerShape(3.dp),
    paddingValues: PaddingValues = PaddingValues(),
): Modifier = this
    .padding(paddingValues)
    .clip(clipShape)
    .background(CampuspickTheme.colors.Gray4.copy(alpha = 0.2f))
    .shimmer(
        baseColor = CampuspickTheme.colors.Gray2.copy(alpha = 0.3f),
        highlightColor = CampuspickTheme.colors.Gray2.copy(alpha = 0.6f),
        animationDuration = 1200
    )