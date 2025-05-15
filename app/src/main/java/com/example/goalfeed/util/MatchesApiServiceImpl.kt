package com.example.goalfeed.util

import android.content.Context
import android.widget.Toast
import com.example.goalfeed.matches.Match
import com.example.goalfeed.matches.MatchesApiResponse
import com.example.goalfeed.R
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MatchesApiServiceImpl @Inject constructor() {
    fun getMatches(
        context: Context,
        onSuccess: (List<Match>) -> Unit,
        onFail:    () -> Unit,
        loadingFinished: () -> Unit
    ) {
        val apiKey  = context.getString(R.string.matches_api_key)
        val baseUrl = context.getString(R.string.matches_api_url)

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val req = chain.request().newBuilder()
                    .addHeader("X-Auth-Token", apiKey)
                    .build()
                chain.proceed(req)
            }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()

        val service = retrofit.create(MatchesApiService::class.java)

        val call = service.getCompetitionMatches(competitionId = "PL")

        call.enqueue(object : Callback<MatchesApiResponse> {
            override fun onResponse(call: Call<MatchesApiResponse>,
                                    response: Response<MatchesApiResponse>) {
                loadingFinished()
                if (response.isSuccessful) {
                    onSuccess(response.body()?.matches.orEmpty())
                } else {
                    onFailure(call, Exception("Bad request"))
                }
            }
            override fun onFailure(call: Call<MatchesApiResponse>, t: Throwable) {
                Toast.makeText(context, "Can't load matches", Toast.LENGTH_SHORT).show()
                onFail()
                loadingFinished()
            }
        })
    }
}

