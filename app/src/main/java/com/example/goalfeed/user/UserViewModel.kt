package com.example.goalfeed.user

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.ClearCredentialException
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalfeed.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "UserViewModel"

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@HiltViewModel
class UserViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val credentialManager: CredentialManager = CredentialManager.create(appContext)

    private val _userData = MutableStateFlow(auth.currentUser)
    val userData = _userData.asStateFlow()

    /**
     * Pass in an Activity context here so the One-Tap UI can attach to a window.
     */
    fun launchCredentialManager(windowContext: Context) {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(appContext.getString(R.string.google_server_id))
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        viewModelScope.launch {
            try {
                val result = credentialManager.getCredential(
                    context = windowContext,
                    request = request
                )
                handleSignIn(result.credential)
            } catch (e: GetCredentialException) {
                Log.e(TAG, "Couldn't retrieve credentials: ${e.localizedMessage}")
            }
        }
    }

    private fun handleSignIn(credential: Credential) {
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val idToken = GoogleIdTokenCredential.createFrom(credential.data).idToken
            firebaseAuthWithGoogle(idToken)
        } else {
            Log.w(TAG, "Credential is not a Google ID token!")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val firebaseCred = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(firebaseCred)
            .addOnCompleteListener { task ->
                viewModelScope.launch {
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithCredential:success")
                        _userData.emit(auth.currentUser)
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        _userData.emit(null)
                    }
                }
            }
    }

    fun signOut() {
        auth.signOut()
        viewModelScope.launch {
            try {
                credentialManager.clearCredentialState(ClearCredentialStateRequest())
                _userData.emit(null)
            } catch (e: ClearCredentialException) {
                Log.e(TAG, "Couldn't clear credentials: ${e.localizedMessage}")
            }
        }
    }
}
