package com.isaacsufyan.androidarchitecture.mvvm.movieapp.di

import android.content.Context
import androidx.room.Room
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.datastore.db.MovieDao
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.datastore.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME: String = "MovieDatabase"

    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCacheDAO(blogDatabase: MovieDatabase): MovieDao {
        return blogDatabase.movieDao()
    }

}