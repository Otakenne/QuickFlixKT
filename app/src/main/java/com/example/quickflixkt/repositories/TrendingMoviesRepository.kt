package com.example.quickflixkt.repositories

import androidx.lifecycle.*
import com.example.quickflixkt.dao.TrendingMoviesDao
import com.example.quickflixkt.models.TrendingMovie
import com.example.quickflixkt.network.MoviesAPI
import com.example.quickflixkt.utility.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrendingMoviesRepository(val trendingMoviesDao: TrendingMoviesDao) {
    var trendingMovies: LiveData<List<TrendingMovie>> = trendingMoviesDao.getAllTrendingMovies().asLiveData()

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

    suspend fun getTrendingMovies() {
        return withContext(Dispatchers.IO) {
            try {
                val trendingMoviesResults = MoviesAPI.retrofitService.getTrendingMovies(Constants.TMDB_API_KEY)
                deleteAllTrendingMovies()
                insertTrendingMovies(trendingMoviesResults.results)
                trendingMovies = trendingMoviesDao.getAllTrendingMovies().asLiveData()
//                return@withContext trendingMovies
            } catch (exception: Exception) {
                print(exception.localizedMessage)
//                return@withContext trendingMovies
            }
        }
    }
}