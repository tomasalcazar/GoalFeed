package com.example.goalfeed.util


import android.content.Context
import android.widget.Toast
import com.example.goalfeed.R
import com.example.goalfeed.matches.ApiFootballMatch
import com.example.goalfeed.matches.ApiFootballResponse
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MatchesApiServiceImpl @Inject constructor() {

    fun getMatches(
        context: Context,
        onSuccess: (List<ApiFootballMatch>) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit
    ) {
        val apiKey = context.getString(R.string.matches_api_key)

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-apisports-key", apiKey)
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://v3.football.api-sports.io/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiFootballService::class.java)
        val call = service.getLiveFixtures("all")

        call.enqueue(object : Callback<ApiFootballResponse> {
            override fun onResponse(
                call: Call<ApiFootballResponse>,
                response: Response<ApiFootballResponse>
            ) {
                if (response.isSuccessful) {
                    val matches = response.body()?.response ?: emptyList()
                    onSuccess(matches)
                } else {
                    Toast.makeText(context, "Error loading live matches", Toast.LENGTH_SHORT).show()
                    onFail()
                }
                loadingFinished()
            }

            override fun onFailure(call: Call<ApiFootballResponse>, t: Throwable) {
                Toast.makeText(context, "API request failed", Toast.LENGTH_SHORT).show()
                onFail()
                loadingFinished()
            }
        })
    }
}
