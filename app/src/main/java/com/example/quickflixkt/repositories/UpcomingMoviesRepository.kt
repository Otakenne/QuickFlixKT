package com.example.quickflixkt.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.quickflixkt.dao.UpcomingMoviesDao
import com.example.quickflixkt.models.UpcomingMovie
import com.example.quickflixkt.network.MoviesAPI
import com.example.quickflixkt.utility.Constants
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpcomingMoviesRepository(private val upcomingMoviesDao: UpcomingMoviesDao) {
    var upcomingMovies = upcomingMoviesDao.getAllUpcomingMovies().asLiveData()

    private val _status = MutableLiveData(LoadStatus.LOADING)
    val status: LiveData<LoadStatus> = _status

    private suspend fun deleteAllUpcomingMovies() {
        return withContext(Dispatchers.IO) {
            upcomingMoviesDao.deleteAllUpcomingMovies()
        }
    }

    private suspend fun insertUpcomingMovies(upcomingMovies: List<UpcomingMovie>) {
        return withContext(Dispatchers.IO) {
            upcomingMoviesDao.insertUpcomingMovie(upcomingMovies)
        }
    }

    suspend fun refreshUpcomingMovies() {
        return withContext(Dispatchers.IO) {
            _status.postValue(LoadStatus.LOADING)
            try {
                val upcomingMoviesResults = MoviesAPI.retrofitService.getUpcomingMovies(Constants.TMDB_API_KEY)
//                deleteAllUpcomingMovies()
                insertUpcomingMovies(upcomingMoviesResults.results)
                _status.postValue(LoadStatus.DONE)
            } catch (exception: Exception) {
                print(exception.localizedMessage)
                _status.postValue(LoadStatus.ERROR)
            }
        }
    }
}