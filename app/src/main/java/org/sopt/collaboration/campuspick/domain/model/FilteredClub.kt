package org.sopt.collaboration.campuspick.domain.model

data class FilteredClub(
    val id: Int,
    val name: String,
    val image: String,
    val category: String,
    val region: String,
    val clubDay: String,
    val postTitle: String,
    val postImage: String,
    val viewCount: Int,
    val commentCount: Int,
    val dDay: Int
)