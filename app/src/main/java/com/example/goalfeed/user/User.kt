package com.example.goalfeed.user

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

@Composable
fun User(
    userViewModel: UserViewModel = hiltViewModel(),
    favoriteTeamsViewModel: FavoriteTeamsViewModel
) {
    val allTeams by userViewModel.allTeams.collectAsState()
    val favoriteTeams by favoriteTeamsViewModel.favoriteTeams.collectAsState()
    val favoriteTeamIds = remember(favoriteTeams) { favoriteTeams.map { it.id } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Selecciona tus equipos NBA favoritos",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
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
                                favoriteTeamsViewModel.removeFavorite(team)
                            } else {
                                favoriteTeamsViewModel.addFavorite(team)
                            }
                        }
                        .padding(vertical = 8.dp, horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Nombre del equipo con peso (¡ahora sí se ve!)
                    Text(
                        text = team.name,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = if (isFavorite) "Favorito" else "No favorito",
                        tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                    )
                }
            }
        }
    }
}
