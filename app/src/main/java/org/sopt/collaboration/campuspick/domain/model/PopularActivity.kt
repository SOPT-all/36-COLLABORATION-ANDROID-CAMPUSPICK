package org.sopt.collaboration.campuspick.domain.model

import org.sopt.collaboration.campuspick.R

data class PopularActivity(
    val id: Int,
    val title: String,
    val viewCount: Int,
    val commentCount: Int,
    val image: String
)

enum class PopularActivityImage(
    val image: String,
    val imagePoster: Int
) {
    BUSANGLOBAL(
        image = "activity_busanglobal",
        imagePoster = R.drawable.img_activity_busanglobal
    ),
    LISOLAB(
        image = "activity_lisolab",
        imagePoster = R.drawable.img_activity_lisolab
    ),
    ZENIX(
        image = "activity_zenix",
        imagePoster = R.drawable.img_activity_zenix
    ),
    SPECTRA(
        image = "activity_spectra",
        imagePoster = R.drawable.img_activity_spectra
    ),
    LOCALSNAP(
        image = "activity_localsnap",
        imagePoster = R.drawable.img_activity_localsnap
    );

    companion object {
        fun getImageId(image: String): Int {
            return entries.find {
                it.image == image
            }?.imagePoster ?: R.drawable.ic_launcher_background
        }
    }
}