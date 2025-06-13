package com.example.goalfeed.user

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.goalfeed.data.FavoriteTeam
import com.example.goalfeed.util.TeamsApiServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val apiService = TeamsApiServiceImpl()

    private val _allTeams = MutableStateFlow<List<FavoriteTeam>>(emptyList())
    val allTeams: StateFlow<List<FavoriteTeam>> = _allTeams

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    // Ligas principales de Europa
    private val leagueIds = listOf(
        39,   // Premier League
        140,  // La Liga
        78,   // Bundesliga
        135,  // Serie A
        61    // Ligue 1
    )
    private val season = 2023

    init {
        fetchAllTeams(application)
    }

    private fun fetchAllTeams(application: Application) {
        val teamsAccumulator = mutableListOf<FavoriteTeam>()
        var processed = 0
        _loading.value = true
        leagueIds.forEach { leagueId ->
            apiService.getTeams(
                context = application,
                leagueId = leagueId,
                season = season,
                onSuccess = { teams ->
                    Log.d("UserVM", "Equipos recibidos para liga $leagueId: ${teams.size}")
                    teams.take(3).forEach { Log.d("UserVM", "Equipo: ${it.name} (${it.id})") }
                    teamsAccumulator.addAll(teams)
                    processed++
                    if (processed == leagueIds.size) {
                        _allTeams.value = teamsAccumulator.distinctBy { it.id }
                        Log.d("UserVM", "Equipos totales emitidos: ${_allTeams.value.size}")
                        _loading.value = false
                    }
                },
                onFail = {
                    Log.e("UserVM", "ERROR: Falló la API para liga $leagueId")
                    processed++
                    if (processed == leagueIds.size) {
                        _allTeams.value = teamsAccumulator.distinctBy { it.id }
                        Log.d("UserVM", "Equipos totales emitidos (fallo): ${_allTeams.value.size}")
                        _loading.value = false
                    }
                },
                loadingFinished = {}
            )
        }
    }
}
