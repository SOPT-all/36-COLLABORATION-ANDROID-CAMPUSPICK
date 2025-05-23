package org.sopt.collaboration.campuspick.data.response

import kotlinx.serialization.Serializable

@Serializable
data class GetSearchClubsResponseDto(
    val clubInfo: ClubInfo,
    val recruitPost: RecruitPost
)

@Serializable
data class ClubInfo(
    val id: Int,
    val name: String,
    val image: String,
    val category: String,
    val region: String,
    val clubDay: String
)

@Serializable
data class RecruitPost(
    val id: Int,
    val title: String,
    val image: String,
    val viewCount: Int,
    val commentCount: Int,
    val dDay: Int
)

@Serializable
data class PopularActivity(
    val id: Int,
    val title: String,
    val viewCount: Int,
    val commentCount: Int,
    val image: String
)
