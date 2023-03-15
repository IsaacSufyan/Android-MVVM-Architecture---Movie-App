package com.isaacsufyan.androidarchitecture.mvvm.movieapp.di

import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.datastore.db.MovieDao
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.datastore.net.MoviesApi
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.repo.MovieRepository
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.repo.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepo(
        apiService: MoviesApi,
        cacheService: MovieDao,
    ): MovieRepository {
        return MovieRepositoryImpl(
            apiService,
            cacheService
        )
    }
}