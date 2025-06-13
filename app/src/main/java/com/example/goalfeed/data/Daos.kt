package com.example.goalfeed.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteTeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(team: FavoriteTeam)

    @Delete
    suspend fun delete(team: FavoriteTeam)

    @Query("SELECT * FROM favorite_teams")
    fun getAll(): Flow<List<FavoriteTeam>>
}
