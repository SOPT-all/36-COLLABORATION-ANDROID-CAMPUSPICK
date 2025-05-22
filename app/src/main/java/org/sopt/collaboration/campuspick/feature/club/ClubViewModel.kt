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

    private val _clubRanking = MutableStateFlow<List<ClubRanking>>(listOf())
    val clubRanking: StateFlow<List<ClubRanking>> = _clubRanking.asStateFlow()

    val clubRecruitmentDummy = listOf(
        ClubRecruitment(
            id = 0,
            title = "[OVAL KOREA] 41th PD Staff Recruiting",
            viewCount = 1647,
            commentCount = 13,
            image = "club_recruit_OVALKOREA",
            dday = 17
        ),
        ClubRecruitment(
            id = 0,
            title = "댄스동아리 블랭크 BLANK에서 신입 멤버를 찾습니다!",
            viewCount = 936,
            commentCount = 8,
            image = "club_recruit_BLANK",
            dday = 20
        ),
        ClubRecruitment(
            id = 0,
            title = "플레이박스에서 게임 기획 같이 하자!",
            viewCount = 847,
            commentCount = 4,
            image = "club_recruit_PLAYBOX",
            dday = 20
        ),
    )

    val clubSearchDummy = listOf(
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
        ClubSearch(
            tags = listOf("기타", "수도권"),
            profile = R.drawable.ic_launcher_background,
            author = "플레이박스",
            content = "플레이박스 16.5기 모집",
            dDay = 7,
            viewCount = 144,
            commentCount = 187,
            poster = R.drawable.ic_launcher_background
        ),
        ClubSearch(
            tags = listOf("문화/예술/공연", "수도권"),
            profile = R.drawable.ic_launcher_background,
            author = "bethenose",
            content = "향수 좋아하세요? bethenose 1기 모집합니다.",
            dDay = 7,
            viewCount = 144,
            commentCount = 187,
            poster = R.drawable.ic_launcher_background
        ),
    )

    init {
        viewModelScope.launch {
            campusPickRepository.getRankClubs()
                .onSuccess {
                    _clubRanking.value = it
                    Log.d("ClubVM", it.toString())
                }
                .onFailure {
                    Log.d("ClubVM", it.toString())
                }
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