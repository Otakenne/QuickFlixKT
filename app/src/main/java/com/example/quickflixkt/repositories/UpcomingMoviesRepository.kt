package com.example.quickflixkt.repositories

import androidx.lifecycle.asLiveData
import com.example.quickflixkt.dao.UpcomingMoviesDao
import com.example.quickflixkt.models.UpcomingMovie
import com.example.quickflixkt.network.MoviesAPI
import com.example.quickflixkt.utility.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpcomingMoviesRepository(private val upcomingMoviesDao: UpcomingMoviesDao) {
    var upcomingMovies = upcomingMoviesDao.getAllUpcomingMovies().asLiveData()

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
            try {
                val upcomingMoviesResults = MoviesAPI.retrofitService.getUpcomingMovies(Constants.TMDB_API_KEY)
                deleteAllUpcomingMovies()
                insertUpcomingMovies(upcomingMoviesResults.results)
                upcomingMovies = upcomingMoviesDao.getAllUpcomingMovies().asLiveData()
            } catch (exception: Exception) {
                print(exception.localizedMessage)
            }
        }
    }
}