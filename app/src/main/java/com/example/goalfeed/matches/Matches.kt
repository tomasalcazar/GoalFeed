package com.example.goalfeed.matches

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalfeed.view.models.MatchesViewModel

@Composable
fun Matches(viewModel: MatchesViewModel = hiltViewModel()) {
    val matches by viewModel.matches.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val showRetry by viewModel.showRetry.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            showRetry -> {
                Button(
                    onClick = { viewModel.retryApiCall() },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text("Retry")
                }
            }
            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(matches) { matchItem ->
                        MatchCard(matchItem)
                    }
                    item { Spacer(modifier = Modifier.height(48.dp)) }
                }
            }
        }
    }
}
