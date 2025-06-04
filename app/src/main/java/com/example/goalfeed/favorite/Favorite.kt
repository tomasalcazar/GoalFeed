package com.example.goalfeed.favorite

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.goalfeed.matches.Match
import com.example.goalfeed.matches.MatchesViewModel
import com.example.goalfeed.matches.MatchCard
import com.example.goalfeed.ui.theme.spacingMedium

@Composable
fun Favorite(
    onMatchClick: (Match) -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel(),
    matchesViewModel: MatchesViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    // --- 1) BIOMETRIC AUTH (igual que antes) ---
    val isAuthenticated by viewModel.isAuthenticated.collectAsStateWithLifecycle(initialValue = false)
    LaunchedEffect(Unit) {
        viewModel.authenticate(context)
    }

    val biometricManager = remember { BiometricManager.from(context) }
    val isBiometricAvailable by remember {
        mutableStateOf(biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL))
    }

    when (isBiometricAvailable) {
        BiometricManager.BIOMETRIC_SUCCESS -> {
            if (!isAuthenticated) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "You need to authenticate")
                }
            } else {
                FavoritesContent(
                    viewModel = viewModel,
                    matchesViewModel = matchesViewModel,
                    onMatchClick = onMatchClick
                )
            }
        }
        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
            Text(text = "This phone is not prepared for biometric features")
        }
        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
            Text(text = "Biometric auth is unavailable")
        }
        BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
            Text(text = "You can't use biometric auth until you update security settings")
        }
        BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
            Text(text = "You can't use biometric auth on this Android version")
        }
        BiometricManager.BIOMETRIC_STATUS_UNKNOWN,
        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
            Text(text = "You can't use biometric auth")
        }
    }
}

@Composable
private fun FavoritesContent(
    viewModel: FavoriteViewModel,
    matchesViewModel: MatchesViewModel,
    onMatchClick: (Match) -> Unit
) {

    val allMatches by matchesViewModel.matches.collectAsStateWithLifecycle(initialValue = emptyList())

    val favoriteTeams by viewModel.favoriteTeamsFlow.collectAsStateWithLifecycle(initialValue = emptyList())

    val favoriteNamesSet = remember(favoriteTeams) {
        favoriteTeams.map { it.teamName }.toSet()
    }

    val favoriteMatches = remember(allMatches, favoriteNamesSet) {
        allMatches.filter { match ->
            match.homeTeam.name in favoriteNamesSet ||
                    match.awayTeam.name in favoriteNamesSet
        }
    }

    if (favoriteNamesSet.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "No tienes equipos marcados como favoritos")
        }
    } else if (favoriteMatches.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "No hay partidos para tus equipos favoritos")
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(spacingMedium),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(favoriteMatches) { matchItem ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMatchClick(matchItem) }
                ) {
                    MatchCard(matchItem = matchItem)
                }
            }
        }
    }
}
