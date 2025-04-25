package com.example.goalfeed.util

import com.example.goalfeed.matches.MatchesApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchesApiService {
    @GET("matches")
    fun getAllMatches(
        @Query("status") status: String = "SCHEDULED"
    ): Call<MatchesApiResponse>

    @GET("competitions/{compId}/matches")
    fun getCompetitionMatches(
        @Path("compId") competitionId: String = "PL",
        @Query("status")  status: String = "SCHEDULED",
        @Query("matchday") matchday: Int? = null
    ): Call<MatchesApiResponse>
}

