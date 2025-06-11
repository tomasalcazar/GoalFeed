package com.example.goalfeed.security

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

import android.util.Log

class BiometricAuthManager @Inject constructor() {
    fun authenticate(
        activity: FragmentActivity,
        onError: () -> Unit,
        onSuccess: () -> Unit,
        onFail: () -> Unit
    ) {
        val executor = ContextCompat.getMainExecutor(activity)
        val biometricPrompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    Log.e("BiometricPrompt", "onAuthenticationError: $errorCode $errString")
                    onError()
                }
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    Log.i("BiometricPrompt", "onAuthenticationSucceeded")
                    onSuccess()
                }
                override fun onAuthenticationFailed() {
                    Log.w("BiometricPrompt", "onAuthenticationFailed")
                    onFail()
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .setTitle("Biometric Authentication")
            .setSubtitle("Log in using your biometric credentials")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}
