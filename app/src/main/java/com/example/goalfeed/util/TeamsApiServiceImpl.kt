package com.example.goalfeed.util

import android.content.Context
import android.widget.Toast
import com.example.goalfeed.R
import com.example.goalfeed.data.FavoriteTeam
import com.example.goalfeed.user.TeamsApiResponse
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class TeamsApiServiceImpl {

    fun getTeams(
        context: Context,
        leagueId: Int,
        season: Int,
        onSuccess: (List<FavoriteTeam>) -> Unit,
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
            .baseUrl(context.getString(R.string.matches_api_url))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(TeamsApiService::class.java)
        val call = service.getTeams(leagueId, season)

        call.enqueue(object : Callback<TeamsApiResponse> {
            override fun onResponse(
                call: Call<TeamsApiResponse>,
                response: Response<TeamsApiResponse>
            ) {
                if (response.isSuccessful) {
                    val teams = response.body()?.response?.map { entry ->
                        FavoriteTeam(
                            id = entry.team.id,
                            name = entry.team.name,
                            logo = entry.team.logo
                        )
                    } ?: emptyList()
                    onSuccess(teams)
                } else {
                    Toast.makeText(context, "Error loading teams", Toast.LENGTH_SHORT).show()
                    onFail()
                }
                loadingFinished()
            }

            override fun onFailure(call: Call<TeamsApiResponse>, t: Throwable) {
                Toast.makeText(context, "API request failed", Toast.LENGTH_SHORT).show()
                onFail()
                loadingFinished()
            }
        })
    }
}
