package com.example.goalfeed.favorite

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun Favorite() {
    val context = LocalContext.current

    val viewModel = hiltViewModel<FavoriteViewModel>()
    val isAuthenticated by viewModel.isAuthenticated.collectAsStateWithLifecycle()
    val user by viewModel.userData.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.authenticate(context)
    }

    val biometricManager = remember { BiometricManager.from(context) }

    val isBiometricAvailable = remember {
        biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
    }
    when (isBiometricAvailable) {
        BiometricManager.BIOMETRIC_SUCCESS -> {
            // Biometric features are available
            if(isAuthenticated) {
                Column {
                    Text(text = "Super classified data that only authenticated in users can access")
                    Text(user?.displayName ?: "")
                }
            } else {
                Text(text = "You need to authenticate")
            }
        }

        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
            // No biometric features available on this device
            Text(text = "This phone is not prepared for biometric features")
        }

        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
            // Biometric features are currently unavailable.
            Text(text = "Biometric auth is unavailable")
        }

        BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
            // Biometric features available but a security vulnerability has been discovered
            Text(text = "You can't use biometric auth until you have updated your security details")
        }

        BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
            // Biometric features are currently unavailable because the specified options are incompatible with the current Android version..
            Text(text = "You can't use biometric auth with this Android version")
        }

        BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
            // Unable to determine whether the user can authenticate using biometrics
            Text(text = "You can't use biometric auth")
        }

        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
            // The user can't authenticate because no biometric or device credential is enrolled.
            Text(text = "You can't use biometric auth")
        }
    }
}