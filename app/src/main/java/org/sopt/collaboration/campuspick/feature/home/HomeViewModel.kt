package org.sopt.collaboration.campuspick.feature.home

import kotlinx.collections.immutable.persistentListOf
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.ui.base.BaseViewModel
import org.sopt.collaboration.campuspick.domain.model.Popularity

class HomeViewModel: BaseViewModel<HomeState, HomeSideEffect>(HomeState()) {

    fun updatePopularContestList(list: List<Popularity>) {
        intent {
            copy(popularContestList = list)
        }
    }

    init {
        val popularContestDummy = persistentListOf(
            Popularity(
                id = 1,
                image = R.drawable.img_home_contest1,
                title = "⭐\uFE0F별빛 2025 5월 신입 모집⭐\uFE0F",
                viewCount = 67,
                commentCount = 335
            ),
            Popularity(
                id = 2,
                image = R.drawable.img_home_contest2,
                title = "⭐\uFE0F별빛 2025 5월 신입 모집⭐\uFE0F",
                viewCount = 67,
                commentCount = 335
            ),
            Popularity(
                id = 3,
                image = R.drawable.img_home_contest3,
                title = "⭐\uFE0F별빛 2025 5월 신입 모집⭐\uFE0F",
                viewCount = 67,
                commentCount = 335
            ),
            Popularity(
                id = 4,
                image = R.drawable.img_home_contest1,
                title = "⭐\uFE0F별빛 2025 5월 신입 모집⭐\uFE0F",
                viewCount = 67,
                commentCount = 335
            ),
            Popularity(
                id = 5,
                image = R.drawable.img_home_contest2,
                title = "⭐\uFE0F별빛 2025 5월 신입 모집⭐\uFE0F",
                viewCount = 67,
                commentCount = 335
            ),
            Popularity(
                id = 6,
                image = R.drawable.img_home_contest3,
                title = "⭐\uFE0F별빛 2025 5월 신입 모집⭐\uFE0F",
                viewCount = 67,
                commentCount = 335
            )
        )
        updatePopularContestList(popularContestDummy)
    }

    fun navigateToClub() = postSideEffect(HomeSideEffect.NavigateClub)
}