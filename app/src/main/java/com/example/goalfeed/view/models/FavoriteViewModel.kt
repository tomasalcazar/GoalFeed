package com.example.goalfeed.view.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalfeed.matches.MatchItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor() : ViewModel() {

    private val favoriteTeams = listOf("Real Madrid", "Milan", "Boca Juniors")

    private val _favoriteMatches = MutableStateFlow<List<MatchItem>>(emptyList())
    val favoriteMatches: StateFlow<List<MatchItem>> = _favoriteMatches.asStateFlow()

    init {
        loadFavoriteMatches()
    }

    private fun loadFavoriteMatches() {
        val allMatches = listOf(
            MatchItem("Tue, Apr 2", "21:00", "Real Madrid", "Man City", 3, 3, "FT"),
            MatchItem("Wed, Apr 3", "21:00", "Arsenal", "Bayern", 2, 2, "FT"),
            MatchItem("Thu, Apr 4", "21:00", "Milan", "Roma", null, null, "Upcoming"),
            MatchItem("Fri, Apr 5", "19:00", "Boca Juniors", "San Lorenzo", null, null, "Upcoming")
        )

        viewModelScope.launch {
            _favoriteMatches.emit(
                allMatches.filter {
                    it.homeTeam in favoriteTeams || it.awayTeam in favoriteTeams
                }
            )
        }
    }
}
