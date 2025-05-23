package org.sopt.collaboration.campuspick.feature.club

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.ui.base.BaseViewModel
import org.sopt.collaboration.campuspick.domain.model.ClubRanking
import org.sopt.collaboration.campuspick.domain.model.ClubRecruitment
import org.sopt.collaboration.campuspick.domain.model.ClubSearch
import org.sopt.collaboration.campuspick.domain.repository.CampusPickRepository

class ClubViewModel(
    private val campusPickRepository: CampusPickRepository
) : BaseViewModel<ClubState, ClubSideEffect>(ClubState()) {

    private val _clubRecruitment = MutableStateFlow<List<ClubRecruitment>>(listOf())
    val clubRecruitment: StateFlow<List<ClubRecruitment>> = _clubRecruitment.asStateFlow()

    private val _clubRanking = MutableStateFlow<List<ClubRanking>>(listOf())
    val clubRanking: StateFlow<List<ClubRanking>> = _clubRanking.asStateFlow()

    val clubSearchDummy = listOf(
        ClubSearch(
            tags = listOf("기타", "수도권"),
            profile = R.drawable.img_club_cresol,
            author = "콕티에르",
            content = "\uD83C\uDF7B라이프 스타일 주류 커뮤니티 [COQUETIER] 21.5기 모집\uD83C\uDF7B",
            dDay = 7,
            viewCount = 146,
            commentCount = 187,
            poster = R.drawable.img_club_recruit_coquetier
        ),
        ClubSearch(
            tags = listOf("기타", "수도권"),
            profile = R.drawable.img_club_playbox,
            author = "플레이박스",
            content = "플레이박스 16.5기 모집",
            dDay = 7,
            viewCount = 144,
            commentCount = 24,
            poster = R.drawable.img_club_recruit_playbox
        ),
        ClubSearch(
            tags = listOf("문화/예술/공연", "수도권"),
            profile = R.drawable.img_club_bethenose,
            author = "bethenose",
            content = "향수 좋아하세요? bethenose 1기 모집합니다.",
            dDay = 8,
            viewCount = 101,
            commentCount = 18,
            poster = R.drawable.img_club_recruit_bethenose
        ),
    )

    init {
        viewModelScope.launch {
            getClubRecruitment()
        }
        viewModelScope.launch {
            getRankClubs()
        }
    }

    private suspend fun getClubRecruitment() {
        campusPickRepository.getPopularClubs()
            .onSuccess {
                _clubRecruitment.value = it
                Log.d("ClubVM", it.toString())
            }
            .onFailure {
                Log.d("ClubVM", it.toString())
            }
    }

    private suspend fun getRankClubs() {
        campusPickRepository.getRankClubs()
            .onSuccess {
                _clubRanking.value = it
                Log.d("ClubVM", it.toString())
            }
            .onFailure {
                Log.d("ClubVM", it.toString())
            }
    }

    fun selectCategory(index: Int) {
        intent {
            copy(
                selectedTabIndex = index
            )
        }
    }

    fun updateInputSearch(inputSearch: String) {
        intent {
            copy(
                inputSearch = inputSearch
            )
        }
    }
}