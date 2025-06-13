package com.example.goalfeed.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage

@Composable
fun Favorite(
    favoriteTeamsViewModel: FavoriteTeamsViewModel = hiltViewModel()
) {
    val favoriteTeams by favoriteTeamsViewModel.favoriteTeams.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF2BB)) // Fondo bien visible pastel
            .padding(16.dp)
    ) {
        Text(
            "Tus equipos NBA favoritos",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(favoriteTeams) { team ->
                println("DIBUJANDO FAVORITO: ${team.name}") // DEBUG
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = MaterialTheme.shapes.medium)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (!team.logo.isNullOrBlank()) {
                        AsyncImage(
                            model = team.logo,
                            contentDescription = "Logo ${team.name}",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                        )
                        Spacer(Modifier.width(12.dp))
                    }
                    Text(
                        text = team.name,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
