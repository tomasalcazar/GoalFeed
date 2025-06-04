package com.example.goalfeed.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_teams")
data class FavoriteTeam(
    @PrimaryKey
    val teamName: String
)
