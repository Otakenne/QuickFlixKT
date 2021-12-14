package com.example.quickflixkt.repositories

import androidx.lifecycle.*
import com.example.quickflixkt.dao.TrendingMoviesDao
import com.example.quickflixkt.models.TrendingMovie
import com.example.quickflixkt.network.MoviesAPI
import com.example.quickflixkt.utility.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrendingMoviesRepository(private val trendingMoviesDao: TrendingMoviesDao) {
    val trendingMovies: LiveData<List<TrendingMovie>> = trendingMoviesDao.getAllTrendingMovies().asLiveData()

    private suspend fun deleteAllTrendingMovies() {
        return withContext(Dispatchers.IO) {
            trendingMoviesDao.deleteAllTrendingMovies()
        }
    }

    private suspend fun insertTrendingMovies(trendingMovies: List<TrendingMovie>) {
        return withContext(Dispatchers.IO) {
            trendingMoviesDao.insertTrendingMovies(trendingMovies)
        }
    }

    suspend fun getTrendingMovies(): LiveData<List<TrendingMovie>> {
        return withContext(Dispatchers.IO) {
            try {
                val trendingMoviesResults = MoviesAPI.retrofitService.getTrendingMovies(Constants.TMDB_API_KEY)
                deleteAllTrendingMovies()
                insertTrendingMovies(trendingMoviesResults.results)
                return@withContext trendingMoviesDao.getAllTrendingMovies().asLiveData()
            } catch (exception: Exception) {
                return@withContext trendingMoviesDao.getAllTrendingMovies().asLiveData()
            }
        }
    }
}