package org.sopt.collaboration.campuspick.domain.model

import kotlinx.serialization.Serializable
import org.sopt.collaboration.campuspick.R

@Serializable
data class ClubRecruitment(
    val id: Int,
    val title: String,
    val image: String,
    val viewCount: Int,
    val commentCount: Int,
    val dday: Int
)

enum class ClubRecruitImage(
    val imageName: String,
    val imageId: Int
) {
    OVALKOREA(
        imageName = "club_recruit_OVALKOREA",
        imageId = R.drawable.img_club_recruit_ovalkorea
    ),
    BLANK(
        imageName = "club_recruit_BLANK",
        imageId = R.drawable.img_club_recruit_blank
    ),
    PLAYBOX(
        imageName = "club_recruit_PLAYBOX",
        imageId = R.drawable.img_club_recruit_playbox
    ),
    TREMOLO(
        imageName = "club_TREMOLO",
        imageId = R.drawable.img_club_recruit_tremolo
    ),
    FOXSTAR(
        imageName = "club_recruit_foxstar",
        imageId = R.drawable.img_club_recruit_foxstar
    );

    companion object {
        fun getImageId(imageName: String): Int {
            return entries.find {
                it.imageName == imageName
            }?.imageId ?: R.drawable.ic_launcher_background
        }
    }
}