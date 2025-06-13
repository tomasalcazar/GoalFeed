package com.example.goalfeed.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalfeed.favorite.FavoriteTeamsViewModel
import com.example.goalfeed.data.FavoriteTeam

@Composable
fun User(
    userViewModel: UserViewModel = hiltViewModel(),
    favoriteTeamsViewModel: FavoriteTeamsViewModel = hiltViewModel()
) {
    val allTeams by userViewModel.allTeams.collectAsState()
    val favoriteTeams by favoriteTeamsViewModel.favoriteTeams.collectAsState()
    val favoriteTeamIds = remember(favoriteTeams) { favoriteTeams.map { it.id } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background) // O cualquier color que quieras
    ) {
        Text(
            "Selecciona tus equipos favoritos",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground // Así usa el color de texto correcto según el theme
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f), // Así ocupa todo el espacio disponible
            contentPadding = PaddingValues(0.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(allTeams) { team ->
                val isFavorite = team.id in favoriteTeamIds
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (isFavorite) {
                                favoriteTeamsViewModel.removeFavorite(FavoriteTeam(team.id, team.name, team.logo))
                            } else {
                                favoriteTeamsViewModel.addFavorite(FavoriteTeam(team.id, team.name, team.logo))
                            }
                        }
                        .padding(vertical = 8.dp, horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        team.name,
                        color = MaterialTheme.colorScheme.onBackground // Para evitar texto blanco sobre fondo blanco
                    )
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Star else Icons.Filled.Star,
                        contentDescription = if (isFavorite) "Favorito" else "No favorito",
                        tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

