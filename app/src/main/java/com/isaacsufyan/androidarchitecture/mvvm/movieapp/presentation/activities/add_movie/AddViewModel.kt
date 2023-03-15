package com.isaacsufyan.androidarchitecture.mvvm.movieapp.presentation.activities.add_movie

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.repo.MovieRepository
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    var title = ObservableField("")
    var releaseDate = ObservableField("")

    private val _saveMovie = MutableLiveData<Boolean>()
    private val _searchMovie = MutableLiveData<String?>()
    val saveMovie: LiveData<Boolean> = _saveMovie
    val searchMovie: MutableLiveData<String?> = _searchMovie

    fun saveMovie() {
        if (validateCanSaveMovie()) {
            repository.saveMovie(Movie(title = title.get(), releaseDate = releaseDate.get()))
            _saveMovie.postValue(true)
        } else {
            _saveMovie.postValue(false)
        }
    }

    fun openSearchScreen() {
        if (validateCanSaveSearch()) {
            _searchMovie.postValue(title.get())
        } else {
            _searchMovie.postValue(null)
        }
    }

    fun validateCanSaveMovie(): Boolean {
        val title = this.title.get()
        val releaseDate = this.releaseDate.get()

        if (title != null && releaseDate != null) {
            return title.isNotEmpty() && releaseDate.isNotEmpty()
        }
        return false
    }

    fun validateCanSaveSearch(): Boolean {
        val title = this.title.get()
        if (!title.isNullOrBlank()) {
            return true
        }
        return false
    }
}