package com.example.goalfeed.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteTeamDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(fav: FavoriteTeam)

    @Delete
    suspend fun delete(fav: FavoriteTeam)

    @Query("SELECT * FROM favorite_teams ORDER BY teamName ASC")
    fun getAllFavoriteTeams(): Flow<List<FavoriteTeam>>
}
