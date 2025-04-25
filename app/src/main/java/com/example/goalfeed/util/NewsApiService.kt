package com.example.goalfeed.util

import com.example.goalfeed.home.NewsApiResponse
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Query

interface NewsApiService {
    @GET("everything")
    fun getNews(
        @Query("q") query: String = "Premier League",
        @Query("language") language: String = "en",
        @Query("sortBy") sortBy: String = "publishedAt",
//        @Query("sources") sources: String = "bbc-sport,espn,football-italia,talksport",
        @Query("apiKey") apiKey: String
    ): Call<NewsApiResponse>
}
