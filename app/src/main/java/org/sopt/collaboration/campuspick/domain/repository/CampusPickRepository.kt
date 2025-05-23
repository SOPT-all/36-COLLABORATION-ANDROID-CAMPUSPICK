package org.sopt.collaboration.campuspick.domain.repository

import org.sopt.collaboration.campuspick.domain.model.FilteredClub
import org.sopt.collaboration.campuspick.domain.model.PopularActivity

interface CampusPickRepository {
    suspend fun getPopularClubs()
    suspend fun getPopularActivities(): Result<List<PopularActivity>>
    suspend fun getRankClubs()
    suspend fun getSearchClubs(
        keyword: String?,
        category: String?,
        deadlineType: String?,
        region: String?,
        clubDay: String?
    ): Result<List<FilteredClub>>
}