package com.example.goalfeed.user

import android.app.Application
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
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalfeed.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

const val TAG = "UserViewModel"

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
class UserViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val credentialManager = CredentialManager.create(application.applicationContext)

    // expone solo los datos que querés mostrar
    private val _userData = MutableStateFlow(auth.currentUser?.toUserData())
    val userData: StateFlow<UserData?> = _userData

    // --- Favoritos, teams, etc, lo que ya tenías abajo ---
    private val apiService = com.example.goalfeed.util.TeamsApiServiceImpl()
    private val _allTeams = MutableStateFlow<List<com.example.goalfeed.data.FavoriteTeam>>(emptyList())
    val allTeams: StateFlow<List<com.example.goalfeed.data.FavoriteTeam>> = _allTeams

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    init {
        fetchAllTeams(application)
    }

    // --- SOCIAL LOGIN GOOGLE ---
    fun launchCredentialManager() {
        val context = getApplication<Application>()
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(context.getString(R.string.google_server_id)) // Usá tu server client id de Google Cloud
            .setFilterByAuthorizedAccounts(false)
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        viewModelScope.launch {
            try {
                val result = credentialManager.getCredential(
                    context = context,
                    request = request
                )
                handleSignIn(result.credential)
            } catch (e: GetCredentialException) {
                Log.e(TAG, "Couldn't retrieve user's credentials: ${e.localizedMessage}")
            }
        }
    }

    private fun handleSignIn(credential: Credential) {
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
        } else {
            Log.w(TAG, "Credential is not of type Google ID!")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    viewModelScope.launch { _userData.emit(user?.toUserData()) }
                } else {
                    viewModelScope.launch { _userData.emit(null) }
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun signOut() {
        auth.signOut()
        viewModelScope.launch {
            try {
                val clearRequest = ClearCredentialStateRequest()
                credentialManager.clearCredentialState(clearRequest)
                _userData.emit(null)
            } catch (e: ClearCredentialException) {
                Log.e(TAG, "Couldn't clear user credentials: ${e.localizedMessage}")
            }
        }
    }

    // --- FAVORITOS TEAMS, ETC ---
    private fun fetchAllTeams(application: Application) {
        _loading.value = true
        apiService.getTeams(
            context = application,
            onSuccess = { teams ->
                Log.d("UserVM", "NBA Equipos recibidos: ${teams.size}")
                _allTeams.value = teams
                _loading.value = false
            },
            onFail = {
                Log.e("UserVM", "ERROR: Falló la API NBA")
                _allTeams.value = emptyList()
                _loading.value = false
            },
            loadingFinished = {}
        )
    }
}

// --- EXTENSIÓN PARA PASAR FirebaseUser A UserData ---
fun com.google.firebase.auth.FirebaseUser.toUserData(): UserData =
    UserData(
        uid = uid,
        displayName = displayName,
        email = email,
        photoUrl = photoUrl?.toString()
    )
