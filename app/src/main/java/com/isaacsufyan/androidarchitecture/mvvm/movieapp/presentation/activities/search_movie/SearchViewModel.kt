package com.isaacsufyan.androidarchitecture.mvvm.movieapp.presentation.activities.search_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.repo.MovieRepository
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.repo.MovieRepositoryImpl
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    fun searchMovie(query: String): LiveData<List<Movie>?> {
        return repository.searchMovies(query)
    }

    fun saveMovie(movie: Movie) {
        repository.saveMovie(movie)
    }
}