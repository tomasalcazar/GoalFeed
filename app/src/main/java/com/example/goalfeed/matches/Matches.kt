package com.example.goalfeed.matches

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalfeed.ui.theme.*

@Composable
fun Matches(
    viewModel: MatchesViewModel = hiltViewModel(),
    onMatchClick: (Int) -> Unit
) {
    val matches   by viewModel.matches.collectAsState()
    val loading   by viewModel.loading.collectAsState()
    val showRetry by viewModel.showRetry.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // dinámico light/dark
    ) {
        when {
            loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color    = MaterialTheme.colorScheme.primary
                )
            }
            showRetry -> {
                Button(
                    onClick = { viewModel.retryApiCall() },
                    modifier = Modifier.align(Alignment.Center),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor   = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Retry")
                }
            }
            else -> {
                LazyColumn(
                    modifier            = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    verticalArrangement = Arrangement.spacedBy(spacingMedium),
                    contentPadding      = PaddingValues(
                        top    = paddingLarge,
                        bottom = paddingExtraLarge
                    )
                ) {
                    itemsIndexed(matches) { index, matchItem ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onMatchClick(index) }
                        ) {
                            MatchCard(matchItem)
                        }
                    }
                    item { Spacer(modifier = Modifier.height(spacingXXL)) }
                }
            }
        }
    }
}
