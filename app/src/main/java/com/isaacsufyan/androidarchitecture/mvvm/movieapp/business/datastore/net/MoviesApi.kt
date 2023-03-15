package com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.datastore.net

import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") q: String
    ): Call<MoviesResponse>
}