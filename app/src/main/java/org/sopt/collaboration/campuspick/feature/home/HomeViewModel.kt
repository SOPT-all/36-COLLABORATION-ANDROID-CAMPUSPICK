package org.sopt.collaboration.campuspick.feature.home

import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.ui.base.BaseViewModel
import org.sopt.collaboration.campuspick.domain.model.Popularity
import org.sopt.collaboration.campuspick.domain.repository.CampusPickRepository

class HomeViewModel(private val campusPickRepository: CampusPickRepository) :
    BaseViewModel<HomeState, HomeSideEffect>(HomeState()) {

    init {
        getPopularActivity()
        getPopularClub()
    }

    fun getPopularActivity() {
        viewModelScope.launch {
            campusPickRepository.getPopularActivities()
                .onSuccess {
                    intent {
                        copy(popularActivity = it)
                    }
                }
        }
    }

    fun getPopularClub(){
        viewModelScope.launch {
            campusPickRepository.getPopularClubs()
                .onSuccess {
                    intent {
                        copy(popularClub = it)
                    }
                }
        }
    }

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

    fun navigateToClub() = postSideEffect(HomeSideEffect.NavigateClub)
}