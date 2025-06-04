package com.example.goalfeed.user

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.goalfeed.R
import com.example.goalfeed.favorite.FavoriteViewModel
import com.example.goalfeed.matches.MatchesViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@SuppressLint("ContextCastToActivity")
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun User() {
    val vm = hiltViewModel<UserViewModel>()
    val user by vm.userData.collectAsStateWithLifecycle()

    val matchesVm: MatchesViewModel = hiltViewModel()
    val allMatches by matchesVm.matches.collectAsStateWithLifecycle(initialValue = emptyList())

    val favoriteVm: FavoriteViewModel = hiltViewModel()
    val favoriteTeams by favoriteVm.favoriteTeamsFlow.collectAsStateWithLifecycle(initialValue = emptyList())

    val activity = LocalContext.current as Activity

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        if (user == null) {
            GoogleLoginButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                onClick = { vm.launchCredentialManager(activity) }
            )
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                AsyncImage(
                    model = user?.photoUrl,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(Modifier.height(12.dp))
                Text(text = user?.displayName.orEmpty(), style = MaterialTheme.typography.titleMedium)
                Text(text = user?.email.orEmpty(), style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(20.dp))
                Button(onClick = { vm.signOut() }) {
                    Text("Sign out")
                }

                Spacer(Modifier.height(32.dp))

                val teamNames = remember(allMatches) {
                    allMatches
                        .flatMap { listOf(it.homeTeam.name, it.awayTeam.name) }
                        .distinct()
                        .sorted()
                }

                val favSet = remember(favoriteTeams) {
                    favoriteTeams.map { it.teamName }.toSet()
                }

                Text(
                    text = "Tus equipos (marca estrella si quieres que aparezca en Favoritos):",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(bottom = 20.dp)
                ) {
                    items(teamNames) { name ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (name in favSet) {
                                        favoriteVm.removeFavorite(name)
                                    } else {
                                        favoriteVm.addFavorite(name)
                                    }
                                }
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                        ) {
                            Text(
                                text = name,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = if (name in favSet) Icons.Default.Star else Icons.Default.StarBorder,
                                contentDescription = if (name in favSet) "Unfavorite" else "Favorite",
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(end = 8.dp)
                            )
                        }
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
private fun GoogleLoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.dead),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text("Continue with Google")
    }
}
