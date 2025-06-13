package com.example.goalfeed.user

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.goalfeed.favorite.FavoriteTeamsViewModel
import com.example.goalfeed.R

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun User(
    userViewModel: UserViewModel = hiltViewModel(),
    favoriteTeamsViewModel: FavoriteTeamsViewModel = hiltViewModel(),
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit,
) {
    val allTeams by userViewModel.allTeams.collectAsState()
    val favoriteTeams by favoriteTeamsViewModel.favoriteTeams.collectAsState()
    val favoriteTeamIds = remember(favoriteTeams) { favoriteTeams.map { it.id } }
    val userData by userViewModel.userData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // --- Switch para Dark Mode ---
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Modo oscuro", Modifier.weight(1f))
            Switch(
                checked = isDarkMode,
                onCheckedChange = { onToggleDarkMode(it) }
            )
        }
        Spacer(Modifier.height(12.dp))

        // --- SOCIAL LOGIN BLOQUE ---
        if (userData == null) {
            GoogleLoginButton(
                onClick = userViewModel::launchCredentialManager,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (!userData!!.photoUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = userData!!.photoUrl,
                        contentDescription = "",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                }
                Column {
                    Text(userData!!.displayName ?: "", style = MaterialTheme.typography.titleMedium)
                    Text(userData!!.email ?: "", style = MaterialTheme.typography.bodySmall)
                }
                Spacer(Modifier.weight(1f))
                Button(onClick = { userViewModel.signOut() }) {
                    Text("Sign out")
                }
            }
            Spacer(Modifier.height(16.dp))
        }

        // --- FAVORITOS UI COMO SIEMPRE ---
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

@Composable
fun GoogleLoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = MaterialTheme.shapes.medium,
        border = ButtonDefaults.outlinedButtonBorder,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.ic_google_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Continuar con Google")
    }
}
