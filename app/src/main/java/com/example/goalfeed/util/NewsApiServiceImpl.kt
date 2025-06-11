package com.example.goalfeed.util

import android.content.Context
import android.widget.Toast
import com.example.goalfeed.R
import com.example.goalfeed.home.NewsApiResponse
import com.example.goalfeed.home.NewsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NewsApiServiceImpl @Inject constructor() {

    fun getNews(
        context: Context,
        onSuccess: (List<NewsItem>) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.news_api_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NewsApiService::class.java)
        val apiKey = context.getString(R.string.news_api_key)
        val call = service.getNews(apiKey = apiKey)

        call.enqueue(object : Callback<NewsApiResponse> {
            override fun onResponse(
                call: Call<NewsApiResponse>,
                response: Response<NewsApiResponse>
            ) {
                loadingFinished()
                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    onSuccess(articles)
                } else {
                    Toast.makeText(context, "Failed to load news: ${response.code()}", Toast.LENGTH_SHORT).show()
                    onFail()
                }
            }

            override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                Toast.makeText(context, "Can't load news", Toast.LENGTH_SHORT).show()
                onFail()
                loadingFinished()
            }
        })
    }
}
