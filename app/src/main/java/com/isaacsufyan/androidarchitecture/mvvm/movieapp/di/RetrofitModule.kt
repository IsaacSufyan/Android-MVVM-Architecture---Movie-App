package com.isaacsufyan.androidarchitecture.mvvm.movieapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.BuildConfig
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.datastore.net.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG){
                addInterceptor(loggingInterceptor)
            }
        }
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)

}