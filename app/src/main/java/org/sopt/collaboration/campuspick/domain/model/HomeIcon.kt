package org.sopt.collaboration.campuspick.domain.model

import androidx.annotation.DrawableRes
import org.sopt.collaboration.campuspick.R

enum class HomeIcon(@DrawableRes val image: Int, val label: String) {
    CLUB(R.drawable.ic_club, "동아리"),
    ACTIVITY(R.drawable.ic_activity, "대외활동"),
    CONTEST(R.drawable.ic_contest, "공모전"),
    JOB(R.drawable.ic_job, "취업정보")
}