package org.sopt.collaboration.campuspick.domain.model

data class ClubSearch(
    val tags: List<String>,
    val profile: Int,
    val author: String,
    val content: String,
    val dDay: Int,
    val viewCount: Int,
    val commentCount: Int,
    val poster: Int
)
