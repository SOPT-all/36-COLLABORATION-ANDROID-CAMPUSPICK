package org.sopt.collaboration.campuspick.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ClubRecruitment(
    val id: Int,
    val title: String,
    val image: String,
    val viewCount: Int,
    val commentCount: Int,
    val dday: Int
)