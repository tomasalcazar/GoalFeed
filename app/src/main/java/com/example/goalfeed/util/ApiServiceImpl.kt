package com.example.goalfeed.util

import android.content.Context
import android.widget.Toast
import com.example.goalfeed.R
import com.example.goalfeed.home.NewsApiResponse
import com.example.goalfeed.home.NewsItem
import retrofit.*
import javax.inject.Inject

class ApiServiceImpl @Inject constructor() {

    fun getPremierLeagueNews(
        context: Context,
        onSuccess: (List<NewsItem>) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit
    ) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.news_api_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)

        val apiKey = context.getString(R.string.news_api_key)

        val call: Call<NewsApiResponse> = service.getNews(apiKey = apiKey)

        call.enqueue(object : Callback<NewsApiResponse> {
            override fun onResponse(response: Response<NewsApiResponse>?, retrofit: Retrofit?) {
                loadingFinished()
                if (response?.isSuccess == true) {
                    onSuccess(response.body().articles)
                } else {
                    onFailure(Exception("Bad request"))
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context, "Can't load news", Toast.LENGTH_SHORT).show()
                onFail()
                loadingFinished()
            }
        })
    }
}
