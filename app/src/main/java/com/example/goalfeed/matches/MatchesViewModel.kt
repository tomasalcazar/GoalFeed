package com.example.goalfeed.matches

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalfeed.util.MatchesApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiServiceImpl: MatchesApiServiceImpl,
) : ViewModel() {

    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches = _matches.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    private val _showRetry = MutableStateFlow(false)
    val showRetry = _showRetry.asStateFlow()

    init {
        loadMatches()
    }

    fun retryApiCall() = loadMatches()

    private fun loadMatches() {
        _loading.value = true
        apiServiceImpl.getMatches(
            context = context,
            onSuccess = { apiList ->
                viewModelScope.launch {
                    // MAP ApiFootballMatch to your Match model
                    val mapped = apiList.map {
                        Match(
                            utcDate = it.fixture.date,
                            status = it.fixture.status.short,
                            homeTeam = Team(it.teams.home.name),
                            awayTeam = Team(it.teams.away.name),
                            score = Score(
                                FullTimeScore(
                                    it.goals.home,
                                    it.goals.away
                                )
                            )
                        )
                    }
                    Log.d("MatchesVM", "Matches recibidos: ${mapped.size}")
                    _matches.emit(mapped)
                    _showRetry.value = false
                }
            },
            onFail = {
                Log.e("MatchesVM", "Fallo la llamada a la API")
                viewModelScope.launch {
                    _matches.emit(emptyList())
                    _showRetry.value = true
                }
            },
            loadingFinished = {
                _loading.value = false
            }
        )
    }
}
