package com.example.goalfeed.data

import kotlinx.coroutines.flow.Flow

class FavoriteTeamRepository(private val dao: FavoriteTeamDao) {
    fun getAllTeams(): Flow<List<FavoriteTeam>> = dao.getAll()
    suspend fun addTeam(team: FavoriteTeam) = dao.insert(team)
    suspend fun removeTeam(team: FavoriteTeam) = dao.delete(team)
}
