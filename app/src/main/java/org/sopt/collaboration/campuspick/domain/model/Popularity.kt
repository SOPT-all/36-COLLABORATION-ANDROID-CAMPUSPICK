package org.sopt.collaboration.campuspick.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Popularity(
    val id: Int,
    val image: Int,
    val title: String,
    val viewCount: Int,
    val commentCount: Int
)