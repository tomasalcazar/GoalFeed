package com.example.goalfeed.matches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Matches(onClick: (MatchItem) -> Unit) {
    val matches = listOf(
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

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        items(matches) { matchItem ->
            MatchCard(matchItem = matchItem) {
                onClick(matchItem)
            }
        }
    }
}
