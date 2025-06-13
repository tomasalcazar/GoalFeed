package com.example.goalfeed.util

import com.example.goalfeed.user.TeamsApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface TeamsApiService {
    @GET("teams")
    fun getTeams(): Call<TeamsApiResponse>

}

