package com.example.goalfeed.matches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalfeed.view.models.MatchesViewModel

@Composable
fun Matches(viewModel: MatchesViewModel = hiltViewModel()) {
    val matches by viewModel.matches.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(matches) { matchItem ->
            MatchCard(matchItem = matchItem)
        }
        item { Spacer(Modifier.padding(48.dp)) }
    }
}
