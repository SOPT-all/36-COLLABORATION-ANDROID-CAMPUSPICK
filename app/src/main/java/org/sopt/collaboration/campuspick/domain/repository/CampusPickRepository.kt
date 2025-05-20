package org.sopt.collaboration.campuspick.domain.repository

import org.sopt.collaboration.campuspick.domain.model.FilteredClub

interface CampusPickRepository {
    suspend fun getPopularClubs()
    suspend fun getPopularActivities()
    suspend fun getRankClubs()
    suspend fun getSearchClubs(
        keyword: String?,
        category: String?,
        deadlineType: String?,
        region: String?,
        clubDay: String?
    ): Result<List<FilteredClub>>
}