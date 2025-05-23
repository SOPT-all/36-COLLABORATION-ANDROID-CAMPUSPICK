package org.sopt.collaboration.campuspick.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ClubRanking(
    val id: Int,
    val clubName: String,
    val clubIntroduce: String,
    val clubImage: String
)