package com.example.goalfeed.user

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.goalfeed.data.FavoriteTeam
import com.example.goalfeed.util.TeamsApiServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService = TeamsApiServiceImpl()
    private val _allTeams = MutableStateFlow<List<FavoriteTeam>>(emptyList())
    val allTeams: StateFlow<List<FavoriteTeam>> = _allTeams

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    init {
        fetchAllTeams(application)
    }

    private fun fetchAllTeams(application: Application) {
        _loading.value = true
        apiService.getTeams(
            context = application,
            onSuccess = { teams ->
                Log.d("UserVM", "NBA Equipos recibidos: ${teams.size}")
                _allTeams.value = teams
                _loading.value = false
            },
            onFail = {
                Log.e("UserVM", "ERROR: Falló la API NBA")
                _allTeams.value = emptyList()
                _loading.value = false
            },
            loadingFinished = {}
        )
    }
}
