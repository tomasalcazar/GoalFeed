package com.example.goalfeed.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalfeed.data.FavoriteTeam
import com.example.goalfeed.data.FavoriteTeamDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteTeamsViewModel @Inject constructor(
    private val dao: FavoriteTeamDao
) : ViewModel() {

    val favoriteTeams: StateFlow<List<FavoriteTeam>> =
        dao.getAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addFavorite(team: FavoriteTeam) {
        viewModelScope.launch { dao.insert(team) }
    }

    fun removeFavorite(team: FavoriteTeam) {
        viewModelScope.launch { dao.delete(team) }
    }
}
