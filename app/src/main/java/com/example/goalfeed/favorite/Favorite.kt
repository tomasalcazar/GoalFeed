package com.example.goalfeed.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.goalfeed.ui.theme.*

@Composable
fun Favorite(
    favoriteTeamsViewModel: FavoriteTeamsViewModel = hiltViewModel()
) {
    val favoriteTeams by favoriteTeamsViewModel.favoriteTeams.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingMedium)
    ) {
        Text(
            text  = "Tus equipos NBA favoritos",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(heightLarge))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(spacingMedium)) {
            items(favoriteTeams) { team ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
                        .padding(paddingMedium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    team.logo?.takeIf { it.isNotBlank() }?.let { url ->
                        AsyncImage(
                            model              = url,
                            contentDescription = "Logo ${team.name}",
                            modifier           = Modifier
                                .size(heightExtraLarge)
                                .clip(CircleShape)
                        )
                        Spacer(Modifier.width(spacingMedium))
                    }
                    Text(
                        text  = team.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
