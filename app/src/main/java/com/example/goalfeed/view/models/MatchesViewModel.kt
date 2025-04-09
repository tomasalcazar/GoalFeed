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
class MatchesViewModel @Inject constructor() : ViewModel() {

    private val _matches = MutableStateFlow<List<MatchItem>>(emptyList())
    val matches: StateFlow<List<MatchItem>> = _matches.asStateFlow()

    init {
        loadMatches()
    }

    private fun loadMatches() {
        val mock = listOf(
            MatchItem("Tue, Apr 2", "21:00", "Real Madrid", "Manchester City", 3, 3, "FT"),
            MatchItem("Wed, Apr 3", "21:00", "Arsenal", "Bayern Munich", 2, 2, "FT"),
            MatchItem("Thu, Apr 4", "21:00", "Milan", "Roma", null, null, "FT"),
            MatchItem("Fri, Apr 5", "19:00", "Estudiantes", "The Strongest", 2, 0, "Live"),
            MatchItem("Sat, Apr 6", "16:00", "Barcelona", "Las Palmas", 1, 0, "Upcoming"),
            MatchItem("Sun, Apr 7", "18:00", "Boca Juniors", "San Lorenzo", null, null, "Upcoming"),
            MatchItem("Mon, Apr 8", "17:30", "Atlético Nacional", "Libertad", null, null, "Upcoming"),
            MatchItem("Tue, Apr 9", "21:00", "Manchester City", "Real Madrid", null, null, "Upcoming"),
            MatchItem("Wed, Apr 10", "21:00", "Bayern Munich", "Arsenal", null, null, "Upcoming"),
            MatchItem("Thu, Apr 11", "19:30", "River Plate", "Nacional (URU)", null, null, "Upcoming"),
            MatchItem("Sat, Apr 13", "15:00", "Juventus", "Torino", null, null, "Upcoming"),
            MatchItem("Sun, Apr 14", "20:30", "Flamengo", "Corinthians", null, null, "Upcoming")
        )
        viewModelScope.launch {
            _matches.emit(mock)
        }
    }
}
