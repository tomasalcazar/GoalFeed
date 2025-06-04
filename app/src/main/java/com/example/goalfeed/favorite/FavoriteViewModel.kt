package com.example.goalfeed.favorite

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalfeed.data.FavoriteTeam
import com.example.goalfeed.data.FavoriteTeamDao
import com.example.goalfeed.security.BiometricAuthManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val biometricAuthManager: BiometricAuthManager,
    private val favoriteTeamDao: FavoriteTeamDao,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private var _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _userData = MutableStateFlow(auth.currentUser)
    val userData = _userData.asStateFlow()

    fun authenticate(context: Context) {
        biometricAuthManager.authenticate(
            context,
            onError = {
                _isAuthenticated.value = false
                Toast.makeText(context, "There was an error in the authentication", Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                _isAuthenticated.value = true
            },
            onFail = {
                _isAuthenticated.value = false
                Toast.makeText(context, "The authentication failed, try again", Toast.LENGTH_SHORT).show()
            }
        )
    }

    val favoriteTeamsFlow = favoriteTeamDao.getAllFavoriteTeams()

    fun addFavorite(teamName: String) {
        viewModelScope.launch {
            favoriteTeamDao.insert(FavoriteTeam(teamName = teamName))
        }
    }

    fun removeFavorite(teamName: String) {
        viewModelScope.launch {
            favoriteTeamDao.delete(FavoriteTeam(teamName = teamName))
        }
    }
}
