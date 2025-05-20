package org.sopt.collaboration.campuspick.data.api

import org.sopt.collaboration.campuspick.core.network.BaseResponse
import org.sopt.collaboration.campuspick.data.response.GetSearchClubsResponseDto
import org.sopt.collaboration.campuspick.domain.model.ClubRanking
import retrofit2.http.GET
import retrofit2.http.Query

interface CampusPickService {
    @GET("/$API/$V1/$CLUBS/$POPULAR")
    suspend fun getPopularClubs()

    @GET("/$API/$V1/$ACTIVITIES/$POPULAR")
    suspend fun getPopularActivities()

    @GET("/$API/$V1/$CLUBS/$RANK")
    suspend fun getRankClubs(): BaseResponse<List<ClubRanking>>

    @GET("/$API/$V1/$CLUBS/$SEARCH")
    suspend fun getSearchClubs(
        @Query("title") title: String?,
        @Query("category") category: String?,
        @Query("deadlineType") deadlineType: String?,
        @Query("region") region: String?,
        @Query("clubDay") clubDay: String?
    ): BaseResponse<List<GetSearchClubsResponseDto>>

    companion object {
        const val API = "api"
        const val V1 = "v1"
        const val CLUBS = "clubs"
        const val ACTIVITIES = "activities"
        const val POPULAR = "popular"
        const val RANK = "rank"
        const val SEARCH = "search"
    }
}