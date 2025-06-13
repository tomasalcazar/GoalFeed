package com.example.goalfeed.util

import com.example.goalfeed.matches.ApiFootballResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchesApiService {
    @GET("fixtures")
    fun getLiveFixtures(
        @Query("live") live: String = "all"
    ): Call<ApiFootballResponse>
}
