package com.example.quickflixkt.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.quickflixkt.dao.MovieDao
import com.example.quickflixkt.models.Movie
import com.example.quickflixkt.network.MoviesAPI
import com.example.quickflixkt.utility.Constants
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MoviesRepository(
    private val movieID: Int,
    private val movieDao: MovieDao
) {
    var movie = movieDao.getMovie(movieID).asLiveData()

    private val _status = MutableLiveData(LoadStatus.LOADING)
    val status: LiveData<LoadStatus> = _status

    private suspend fun deleteMovie(movieID: Int) {
        return withContext(Dispatchers.IO) {
            movieDao.deleteMovie(movieID)
        }
    }

    private suspend fun insertMovie(movie: Movie) {
        return withContext(Dispatchers.IO) {
            movieDao.insertMovie(movie)
        }
    }

    suspend fun getMovie() {
        return withContext(Dispatchers.IO) {
            _status.postValue(LoadStatus.LOADING)
            try {
                val tmdbMovie = MoviesAPI.retrofitService.getMovie(movieID.toString(), Constants.TMDB_API_KEY)
//                deleteMovie(movieID)
                insertMovie(tmdbMovie)
                _status.postValue(LoadStatus.DONE)
            } catch (exception: Exception) {
                print(exception.localizedMessage)
                _status.postValue(LoadStatus.ERROR)
            }
        }
    }
}