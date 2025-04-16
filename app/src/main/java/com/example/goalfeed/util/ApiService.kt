package com.example.goalfeed.util

import com.example.goalfeed.home.NewsApiResponse
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Query

interface ApiService {
    @GET("everything")
    fun getPremierLeagueNews(
        @Query("q") query: String = "Premier League",
        @Query("language") language: String = "en",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String
    ): Call<NewsApiResponse>
}

