package org.sopt.collaboration.campuspick.feature.club

import org.sopt.collaboration.campuspick.R
import org.sopt.collaboration.campuspick.core.ui.base.BaseViewModel
import org.sopt.collaboration.campuspick.domain.model.ClubRanking
import org.sopt.collaboration.campuspick.domain.model.ClubSearch
import org.sopt.collaboration.campuspick.feature.club.component.ClubSearchCard

class ClubViewModel() : BaseViewModel<ClubState, ClubSideEffect>(ClubState()) {
    val rankingDummy = listOf(
        ClubRanking(
            ranking = 1,
            title = "미팅 놈들",
            description = "MEET, EAT, CONNECT “MEATING PEOPLE”",
            imageId = R.drawable.ic_launcher_background
        ),
        ClubRanking(
            ranking = 2,
            title = "☀\uFE0F오늘, 내일☀\uFE0F",
            description = "서울 및 수도권 지역 문화탐방 동아리 “오늘,내일\"",
            imageId = R.drawable.ic_launcher_background
        ),
        ClubRanking(
            ranking = 3,
            title = "Buddyz",
            description = "전시/문화 관람 동아리로 활동인원 누적 2,500명",
            imageId = R.drawable.ic_launcher_background
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