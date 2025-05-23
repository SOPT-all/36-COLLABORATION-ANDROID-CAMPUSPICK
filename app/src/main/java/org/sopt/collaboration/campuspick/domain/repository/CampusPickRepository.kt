package org.sopt.collaboration.campuspick.domain.repository

import org.sopt.collaboration.campuspick.domain.model.ClubRanking
import org.sopt.collaboration.campuspick.domain.model.ClubRecruitment
import org.sopt.collaboration.campuspick.domain.model.FilteredClub

interface CampusPickRepository {
    suspend fun getPopularClubs(): Result<List<ClubRecruitment>>
    suspend fun getPopularActivities()
    suspend fun getRankClubs(): Result<List<ClubRanking>>
    suspend fun getSearchClubs(
        keyword: String?,
        category: String?,
        deadlineType: String?,
        region: String?,
        clubDay: String?
    ): Result<List<FilteredClub>>
}