package com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.repo

import androidx.lifecycle.LiveData
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.model.Movie

interface MovieRepository {

    fun getSavedMovies(): LiveData<List<Movie>>

    fun saveMovie(movie: Movie)

    fun deleteMovie(movie: Movie)

    fun searchMovies(query: String): LiveData<List<Movie>?>

}