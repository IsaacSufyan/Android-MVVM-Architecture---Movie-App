package com.isaacsufyan.androidarchitecture.mvvm.movieapp.presentation.activities.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.repo.MovieRepository
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val allMovies = MediatorLiveData<List<Movie>>()

    init {
        getAllMovies()
    }

    fun getSavedMovies() = allMovies

    private fun getAllMovies() {
        allMovies.addSource(repository.getSavedMovies()) { movies ->
            allMovies.postValue(movies)
        }
    }

    fun deleteSavedMovies(movie: Movie) {
        repository.deleteMovie(movie)
    }
}