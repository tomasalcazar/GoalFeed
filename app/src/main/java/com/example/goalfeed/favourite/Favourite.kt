package com.example.goalfeed.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.goalfeed.matches.MatchCard
import com.example.goalfeed.matches.MatchItem

@Composable
fun Favorite() {
    val favoriteTeams = listOf("Real Madrid", "Milan", "Boca Juniors")

    val allMatches = listOf(
        MatchItem("Tue, Apr 2", "21:00", "Real Madrid", "Manchester City", 3, 3, "FT"),
        MatchItem("Wed, Apr 3", "21:00", "Arsenal", "Bayern Munich", 2, 2, "FT"),
        MatchItem("Thu, Apr 4", "21:00", "Milan", "Roma", null, null, "FT"),
        MatchItem("Fri, Apr 5", "19:00", "Estudiantes", "The Strongest", 2, 0, "Live"),
        MatchItem("Sat, Apr 6", "16:00", "Barcelona", "Las Palmas", 1, 0, "Upcoming"),
        MatchItem("Sun, Apr 7", "18:00", "Boca Juniors", "San Lorenzo", null, null, "Upcoming")
    )

    val favoriteMatches = allMatches.filter { match ->
        match.homeTeam in favoriteTeams || match.awayTeam in favoriteTeams
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        item { Spacer(Modifier.padding(0.2.dp)) }
        items(favoriteMatches) { matchItem ->
            MatchCard(matchItem = matchItem)
        }
        item { Spacer(Modifier.padding(0.2.dp)) }
    }
}
