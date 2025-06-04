package com.example.goalfeed.storage

import android.content.Context
import com.example.goalfeed.data.FavoriteTeamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@dagger.hilt.android.qualifiers.ApplicationContext ctx: Context): GoalFeedDatabase {
        return GoalFeedDatabase.getDatabase(ctx)
    }

    @Provides
    fun provideFavoriteTeamDao(db: GoalFeedDatabase): FavoriteTeamDao {
        return db.favoriteTeamDao()
    }
}
