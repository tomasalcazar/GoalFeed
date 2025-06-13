package com.example.goalfeed.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.goalfeed.data.FavoriteTeam
import com.example.goalfeed.data.FavoriteTeamDao

@Database(entities = [FavoriteTeam::class], version = 3)
abstract class GoalFeedDatabase : RoomDatabase() {
    abstract fun favoriteTeamDao(): FavoriteTeamDao

    companion object {
        @Volatile
        private var INSTANCE: GoalFeedDatabase? = null

        fun getDatabase(context: Context): GoalFeedDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoalFeedDatabase::class.java,
                    "goalfeed_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
