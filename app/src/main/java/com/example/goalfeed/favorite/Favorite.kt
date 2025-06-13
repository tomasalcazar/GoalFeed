package com.example.goalfeed.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import com.example.goalfeed.matches.Match
import com.example.goalfeed.matches.MatchCard

@Composable
fun Favorite(
    matches: List<Match>,
    viewModel: FavoriteTeamsViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    val favoriteTeamIds by viewModel.favoriteTeamIds.collectAsState()

    val filteredMatches = remember(matches, favoriteTeamIds) {
        matches.filter { match ->
            match.homeTeam.id in favoriteTeamIds || match.awayTeam.id in favoriteTeamIds
        }
    }

    LazyColumn {
        items(filteredMatches) { match ->
            MatchCard(match)
        }
    }
}
