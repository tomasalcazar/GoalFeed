package com.example.goalfeed

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goalfeed.home.NewsFeed
import com.example.goalfeed.home.NewsViewModel
import com.example.goalfeed.ui.theme.*

@Composable
fun MainMenu(onClick: (Int) -> Unit) {
    val context = LocalContext.current
    val activity = context as? FragmentActivity
    val viewModel = hiltViewModel<NewsViewModel>()
    val news by viewModel.news.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val retry by viewModel.showRetry.collectAsState()
    val isAuthenticated by viewModel.isAuthenticated.collectAsState()

    val biometricManager = remember { BiometricManager.from(context) }
    val isBiometricAvailable = remember {
        biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
    }

    LaunchedEffect(activity) {
        if (activity != null && !isAuthenticated) {
            viewModel.authenticate(activity)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingLarge)
    ) {
        when (isBiometricAvailable) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                if (!isAuthenticated) {
                    Text(
                        "Please authenticate to see the news",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    when {
                        loading -> {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                        retry -> {
                            Column(
                                modifier = Modifier.align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("Error loading news", color = MaterialTheme.colorScheme.error)
                                Spacer(modifier = Modifier.height(spacingSmall))
                                Button(onClick = { if (activity != null) viewModel.retryApiCall(activity) }) {
                                    Text("Retry")
                                }
                            }
                        }
                        news.isEmpty() -> {
                            Text("No news available", modifier = Modifier.align(Alignment.Center))
                        }
                        else -> {
                            NewsFeed(newsList = news) { newsItem ->
                                val index = news.indexOf(newsItem)
                                onClick(index)
                            }
                        }
                    }
                }
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Text("This phone is not prepared for biometric features", modifier = Modifier.align(Alignment.Center))
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Text("Biometric auth is unavailable", modifier = Modifier.align(Alignment.Center))
            }
            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                Text("Update your security details for biometric auth", modifier = Modifier.align(Alignment.Center))
            }
            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                Text("Biometric auth is not supported in this Android version", modifier = Modifier.align(Alignment.Center))
            }
            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                Text("Cannot use biometric auth", modifier = Modifier.align(Alignment.Center))
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Text("No biometric or device credential enrolled", modifier = Modifier.align(Alignment.Center))
            }
            else -> {
                Text("Biometric status unknown", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}
