package org.sopt.collaboration.campuspick.domain.model

import kotlinx.serialization.Serializable
import org.sopt.collaboration.campuspick.R

@Serializable
data class ClubRanking(
    val id: Int,
    val clubName: String,
    val clubIntroduce: String,
    val clubImage: String
)

enum class ClubImage(
    val imageName: String,
    val imageId: Int
) {
    OVALKOREA(
        imageName = "club_OVALKOREA",
        imageId = R.drawable.img_home_category_job_info
    ),
    TREMOLO(
        imageName = "club_TREMOLO",
        imageId = R.drawable.ic_launcher_foreground
    ),
    CRESOL(
        imageName = "club_CRESOL",
        imageId = R.drawable.ic_launcher_background
    );

    companion object {
        fun getImageId(imageName: String): Int {
            return entries.find {
                it.imageName == imageName
            }?.imageId ?: R.drawable.ic_launcher_background
        }
    }
}