package com.example.goalfeed.user

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.goalfeed.R
import com.example.goalfeed.favorite.FavoriteTeamsViewModel
import com.example.goalfeed.notification.ScheduleNotificationViewModel
import com.example.goalfeed.ui.theme.*

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun User(
    userViewModel: UserViewModel = hiltViewModel(),
    favoriteTeamsViewModel: FavoriteTeamsViewModel = hiltViewModel(),
    notificationVM: ScheduleNotificationViewModel = hiltViewModel(),
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    val allTeams by userViewModel.allTeams.collectAsState()
    val favoriteTeams by favoriteTeamsViewModel.favoriteTeams.collectAsState()
    val favoriteIds = remember(favoriteTeams) { favoriteTeams.map { it.id } }
    val userData by userViewModel.userData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingLarge)
    ) {
        // Dark mode toggle
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Modo oscuro",
                Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge
            )
            Switch(
                checked = isDarkMode,
                onCheckedChange = onToggleDarkMode,
                colors = SwitchDefaults.colors(
                    uncheckedThumbColor = MaterialTheme.colorScheme.onSurface,
                    checkedThumbColor   = MaterialTheme.colorScheme.primary
                )
            )
        }
        Spacer(Modifier.height(heightMedium))

        // Social login
        if (userData == null) {
            GoogleLoginButton(
                onClick = userViewModel::launchCredentialManager,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(spacingLarge))
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                userData!!.photoUrl.takeIf { !it.isNullOrBlank() }?.let { url ->
                    AsyncImage(
                        model = url,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(heightExtraLarge)
                            .clip(MaterialTheme.shapes.small)
                    )
                    Spacer(Modifier.width(spacingMedium))
                }
                Column {
                    Text(
                        text = userData!!.displayName.orEmpty(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = userData!!.email.orEmpty(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = { userViewModel.signOut() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor   = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Sign out")
                }
            }
            Spacer(Modifier.height(spacingLarge))
        }

        // Favorites header
        Text(
            "Selecciona tus equipos NBA favoritos",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(spacingLarge))

        // Favorites list
        LazyColumn(
            contentPadding      = PaddingValues(0.dp),
            verticalArrangement = Arrangement.spacedBy(spacingSmall)
        ) {
            items(allTeams) { team ->
                val isFav = team.id in favoriteIds
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
                        .clickable {
                            if (isFav) {
                                favoriteTeamsViewModel.removeFavorite(team)
                                notificationVM.notifyNow("Eliminaste a ${team.name} de favoritos")
                            } else {
                                favoriteTeamsViewModel.addFavorite(team)
                                notificationVM.notifyNow("Agregaste a ${team.name} a favoritos")
                            }
                        }
                        .padding(vertical = paddingSmall, horizontal = paddingMedium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    Text(
                        text  = team.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Icon(
                        imageVector        = Icons.Filled.Star,
                        contentDescription = if (isFav) "Favorito" else "No favorito",
                        tint               = if (isFav)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
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
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor   = MaterialTheme.colorScheme.onSurface
        ),
        shape          = MaterialTheme.shapes.medium,
        border         = ButtonDefaults.outlinedButtonBorder,
        contentPadding = PaddingValues(horizontal = paddingLarge, vertical = paddingLarge)
    ) {
        Image(
            painter           = painterResource(R.drawable.ic_google_logo),
            contentDescription = "Logo de Google",
            modifier          = Modifier.size(heightLarge)
        )
        Spacer(Modifier.width(spacingSmall))
        Text("Continuar con Google")
    }
}
