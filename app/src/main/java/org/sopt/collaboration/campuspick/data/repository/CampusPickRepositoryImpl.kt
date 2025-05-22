package org.sopt.collaboration.campuspick.data.repository

import org.sopt.collaboration.campuspick.data.api.CampusPickService
import org.sopt.collaboration.campuspick.domain.model.ClubRanking
import org.sopt.collaboration.campuspick.domain.model.ClubRecruitment
import org.sopt.collaboration.campuspick.domain.model.FilteredClub
import org.sopt.collaboration.campuspick.domain.repository.CampusPickRepository

class CampusPickRepositoryImpl(
    private val campusPickService: CampusPickService
) : CampusPickRepository {
    override suspend fun getPopularClubs(): Result<List<ClubRecruitment>> = runCatching {
        campusPickService.getPopularClubs().data
    }.mapCatching {
        it.map {
            ClubRecruitment(
                id = it.id,
                title = it.title,
                viewCount = it.viewCount,
                commentCount = it.commentCount,
                image = it.image,
                dday = it.dday
            )
        }
    }

    override suspend fun getPopularActivities() {
        TODO("Not yet implemented")
    }

    override suspend fun getRankClubs(): Result<List<ClubRanking>> = runCatching {
        campusPickService.getRankClubs().data
    }.mapCatching {
        it.map {
            ClubRanking(
                id = it.id,
                clubName = it.clubName,
                clubIntroduce = it.clubIntroduce,
                clubImage = it.clubImage,
            )
        }
    }

    override suspend fun getSearchClubs(
        keyword: String?,
        category: String?,
        deadlineType: String?,
        region: String?,
        clubDay: String?
    ): Result<List<FilteredClub>> = runCatching {
        campusPickService.getSearchClubs(
            keyword = keyword,
            category = category,
            deadlineType = deadlineType,
            region = region,
            clubDay = clubDay
        ).data
    }.mapCatching {
        it.map {
            FilteredClub(
                id = it.clubInfo.id,
                name = it.clubInfo.name,
                image = it.clubInfo.image,
                category = it.clubInfo.category,
                region = it.clubInfo.region,
                clubDay = it.clubInfo.clubDay,
                postTitle = it.recruitPost.title,
                postImage = it.recruitPost.image,
                viewCount = it.recruitPost.viewCount,
                commentCount = it.recruitPost.commentCount,
                dDay = it.recruitPost.dDay
            )
        }
    }
}