package com.example.goalfeed.storage

import android.content.Context
import com.example.goalfeed.data.FavoriteTeamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGoalFeedDatabase(@ApplicationContext context: Context): GoalFeedDatabase =
        GoalFeedDatabase.getDatabase(context)

    @Provides
    @Singleton  // <- AGREGA ESTO
    fun provideFavoriteTeamDao(db: GoalFeedDatabase): FavoriteTeamDao =
        db.favoriteTeamDao()
}
