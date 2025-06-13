package com.example.goalfeed.util

import com.example.goalfeed.user.TeamsApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsApiService {
    @GET("teams")
    fun getTeams(
        @Query("league") leagueId: Int,
        @Query("season") season: Int
    ): Call<TeamsApiResponse>
}
