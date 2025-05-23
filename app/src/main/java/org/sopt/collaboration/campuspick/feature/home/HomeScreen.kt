package org.sopt.collaboration.campuspick.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.designsystem.component.cardview.CampuspickCard
import org.sopt.collaboration.campuspick.core.designsystem.theme.CampuspickTheme
import org.sopt.collaboration.campuspick.core.ui.extension.customClickable
import org.sopt.collaboration.campuspick.core.ui.lifecycle.LaunchedEffectWithLifecycle
import org.sopt.collaboration.campuspick.core.ui.preview.DefaultPreview
import org.sopt.collaboration.campuspick.core.viewmodel.ViewModelFactory
import org.sopt.collaboration.campuspick.data.repository.CampusPickRepositoryImpl
import org.sopt.collaboration.campuspick.domain.model.HomeEdu
import org.sopt.collaboration.campuspick.domain.model.HomeIcon
import org.sopt.collaboration.campuspick.domain.model.PopularActivity
import org.sopt.collaboration.campuspick.domain.model.PopularActivityImage
import org.sopt.collaboration.campuspick.domain.model.Popularity
import org.sopt.collaboration.campuspick.domain.repository.CampusPickRepository
import org.sopt.collaboration.campuspick.feature.home.HomeSideEffect.NavigateClub
import org.sopt.collaboration.campuspick.feature.home.component.HomeHeader

@Composable
fun HomeRoute(
    padding: PaddingValues,
    navigateToClub: () -> Unit,
) {
    val viewModel: HomeViewModel = viewModel(factory = ViewModelFactory())
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffectWithLifecycle{
        viewModel.sideEffect.collectLatest { sideEffect ->
            when(sideEffect){
                NavigateClub -> navigateToClub()
            }
        }
        viewModel.getPopularActivity()
    }

    HomeScreen(
        contentPadding = padding,
        navigateToClub = viewModel::navigateToClub,
        state = uiState.value,
        popularContestList = viewModel.popularContestDummy
    )
}

@Composable
fun HomeScreen(
    contentPadding: PaddingValues,
    navigateToClub: () -> Unit,
    state: HomeState,
    popularContestList: ImmutableList<Popularity>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        stickyHeader {
            HomeHeader(
                alarmClick = {},
                modifier = Modifier
                    .background(CampuspickTheme.colors.White)
            )
        }

        item {
            Spacer(modifier = Modifier.height(13.dp))

            EduSection(
                eduPagerState = rememberPagerState { HomeEdu.entries.size },
                eduList = HomeEdu.entries.toImmutableList()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            HomeIconSection(
                homeIcons = HomeIcon.entries.toImmutableList(),
                onIconClick = { homeIcon ->
                    when (homeIcon) {
                        HomeIcon.CLUB -> navigateToClub()
                        HomeIcon.JOB -> {}
                        HomeIcon.ACTIVITY -> {}
                        HomeIcon.CONTEST -> {}
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            PopularSection(
                popularTitle = "인기 동아리",
                contents = popularContestList
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            PopularActivitySection(
                popularTitle = "인기 대외활동",
                contents = state.popularActivity.toImmutableList()
            )
            Spacer(modifier = Modifier.height(20.dp))
        }


        item {
            PopularSection(
                popularTitle = "인기 공모전",
                contents = popularContestList
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        item { EventSection(eventHorizontalPadding = 10.dp) }
    }
}

@Composable
private fun EduSection(
    eduPagerState: PagerState,
    eduList: ImmutableList<HomeEdu>,
    modifier: Modifier = Modifier
) {
    Column {
        HorizontalPager(
            state = eduPagerState,
            modifier = modifier.padding(horizontal = 15.dp)
        ) { page ->
            Image(
                painter = painterResource(id = eduList[page].image),
                contentScale = ContentScale.Crop,
                contentDescription = "edu image",
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .height(95.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(eduPagerState.pageCount) { iteration ->
                val color =
                    if (eduPagerState.currentPage == iteration) CampuspickTheme.colors.Blue else CampuspickTheme.colors.Gray3
                Box(
                    modifier = Modifier
                        .padding(horizontal = 14.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(4.dp)
                )
            }
        }
    }
}

@Composable
private fun HomeIconSection(
    homeIcons: ImmutableList<HomeIcon>,
    onIconClick: (HomeIcon) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        homeIcons.forEach { icon ->
            key(icon) {
                Column(
                    modifier = Modifier
                        .size(72.dp)
                        .customClickable(rippleEnabled = false) {
                            onIconClick(icon)
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(13.dp))
                            .size(44.dp)
                            .background(CampuspickTheme.colors.SkyBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = icon.image),
                            contentDescription = "home icon",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = icon.label,
                        color = CampuspickTheme.colors.Black,
                        style = CampuspickTheme.typography.caption2
                    )
                }
            }
        }
    }
}

@Composable
private fun PopularActivitySection(
    popularTitle: String,
    contents: ImmutableList<PopularActivity>
) {
    Column {
        Text(
            text = popularTitle,
            style = CampuspickTheme.typography.heading4,
            color = CampuspickTheme.colors.Black,
            modifier = Modifier
                .height(35.dp)
                .padding(horizontal = 15.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(contents, key = { "${popularTitle}-${it.id}" }) { content ->
                CampuspickCard(
                    cardBackgroundColor = CampuspickTheme.colors.Gray4,
                    cardImage = painterResource(id = PopularActivityImage.getImageId(content.image)),
                    imageHeight = 106.dp,
                    cardTitle = content.title,
                    cardContentHorizontalPadding = 6.dp,
                    cardContentVerticalPadding = 4.dp,
                    titleMetaSpacer = Modifier.height(6.dp),
                    metaInfoContent = {
                        HomeMetaIntoContent(
                            viewCount = content.viewCount.toString(),
                            commentCount = content.commentCount.toString()
                        )
                    },
                    modifier = Modifier.height(172.dp)
                )
            }
        }
    }
}

@Composable
private fun PopularSection(
    popularTitle: String,
    contents: ImmutableList<Popularity>
) {
    Column {
        Text(
            text = popularTitle,
            style = CampuspickTheme.typography.heading4,
            color = CampuspickTheme.colors.Black,
            modifier = Modifier
                .height(35.dp)
                .padding(horizontal = 15.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(contents, key = { "${popularTitle}-${it.id}" }) { content ->
                CampuspickCard(
                    cardBackgroundColor = CampuspickTheme.colors.Gray4,
                    cardImage = painterResource(id = content.image),
                    imageHeight = 106.dp,
                    cardTitle = content.title,
                    cardContentHorizontalPadding = 6.dp,
                    cardContentVerticalPadding = 4.dp,
                    titleMetaSpacer = Modifier.height(6.dp),
                    metaInfoContent = {
                        HomeMetaIntoContent(
                            viewCount = content.viewCount.toString(),
                            commentCount = content.commentCount.toString()
                        )
                    },
                    modifier = Modifier.height(172.dp)
                )
            }
        }
    }
}

@Composable
private fun HomeMetaIntoContent(
    viewCount: String,
    commentCount: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Image(
            modifier = Modifier
                .size(12.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_post_shown),
            contentDescription = "view"
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = viewCount,
            color = CampuspickTheme.colors.Gray2,
            style = CampuspickTheme.typography.caption3
        )

        Spacer(modifier = Modifier.width(4.dp))

        Image(
            modifier = Modifier
                .size(12.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_post_comment),
            contentDescription = "comment"
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = commentCount,
            color = CampuspickTheme.colors.Gray2,
            style = CampuspickTheme.typography.caption3
        )
    }
}

@Composable
private fun EventSection(
    eventHorizontalPadding: Dp,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = eventHorizontalPadding)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "이벤트")

            Spacer(modifier = Modifier.weight(1f))

            Text(text = "더보기")

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_next_arrow),
                contentDescription = "see more",
                modifier = Modifier.size(12.dp)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        CampuspickCard(
            cardBackgroundColor = CampuspickTheme.colors.Gray5,
            cardImage = painterResource(R.drawable.img_home_event),
            imageHeight = 229.dp,
            cardTitle = "<파이널 데스티네이션 블러드라인> 5/7 (수) \n대개봉 예매권 이벤트",
            cardContentHorizontalPadding = 10.dp,
            cardContentVerticalPadding = 4.dp,
            titleMetaSpacer = Modifier.height(6.dp),
            metaInfoContent = {
                Text(
                    text = "이벤트",
                    style = CampuspickTheme.typography.caption1,
                    color = CampuspickTheme.colors.Gray2
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(294.dp)
        )
    }
}