package com.example.goalfeed.matches

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetail(matchItem: Match) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "${matchItem.homeTeam.name} vs ${matchItem.awayTeam.name}") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            val date = matchItem.utcDate.substringBefore("T")
            val time = matchItem.utcDate
                .substringAfter("T")
                .substringBefore("Z")
                .take(5)

            Text(text = "Date: $date", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Time: $time", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = matchItem.homeTeam.name, style = MaterialTheme.typography.titleLarge)
                        Text(text = matchItem.score.fullTime.home?.toString() ?: "-", style = MaterialTheme.typography.titleLarge)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = matchItem.awayTeam.name, style = MaterialTheme.typography.titleLarge)
                        Text(text = matchItem.score.fullTime.away?.toString() ?: "-", style = MaterialTheme.typography.titleLarge)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Status: ${matchItem.status}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
