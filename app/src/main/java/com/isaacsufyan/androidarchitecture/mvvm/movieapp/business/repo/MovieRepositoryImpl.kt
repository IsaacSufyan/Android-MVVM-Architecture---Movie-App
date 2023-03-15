package com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.BuildConfig
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.datastore.db.MovieDao
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.model.Movie
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.model.MoviesResponse
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.datastore.net.MoviesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class MovieRepositoryImpl(private var apiService: MoviesApi, private var cacheService: MovieDao) :
    MovieRepository {

    private val allMovies: LiveData<List<Movie>> = cacheService.getAll()

    override fun deleteMovie(movie: Movie) {
        thread {
            cacheService.delete(movie.id)
        }
    }

    override fun getSavedMovies() = allMovies

    override fun saveMovie(movie: Movie) {
        thread {
            cacheService.insert(movie)
        }
    }

    override fun searchMovies(query: String): LiveData<List<Movie>?> {

        val data = MutableLiveData<List<Movie>?>()

        apiService.searchMovie(BuildConfig.API_KEY,query).enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                data.value = null
                Log.d(this.javaClass.simpleName, "Failure")
            }

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                data.value = response.body()?.results
                Log.d(this.javaClass.simpleName, "Response: ${response.body()?.results}")
            }
        })
        return data
    }
}