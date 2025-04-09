package com.example.goalfeed.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalfeed.matches.MatchCard
import com.example.goalfeed.matches.MatchItem
import com.example.goalfeed.view.models.FavoriteViewModel

@Composable
fun Favorite(viewModel: FavoriteViewModel = hiltViewModel(), onClick: (MatchItem) -> Unit = {}) {
    val favorites by viewModel.favoriteMatches.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(favorites) { matchItem ->
            MatchCard(matchItem = matchItem)
        }
    }
}
