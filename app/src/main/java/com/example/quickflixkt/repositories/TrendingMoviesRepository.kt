package com.example.quickflixkt.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.quickflixkt.dao.TrendingMoviesDao
import com.example.quickflixkt.models.TrendingMovie
import com.example.quickflixkt.network.MoviesAPI
import com.example.quickflixkt.utility.Constants
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrendingMoviesRepository(private val trendingMoviesDao: TrendingMoviesDao) {
    var trendingMovies = trendingMoviesDao.getAllTrendingMovies().asLiveData()

    private val _status = MutableLiveData(LoadStatus.LOADING)
    val status: LiveData<LoadStatus> = _status

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
            _status.postValue(LoadStatus.LOADING)
            try {
                val trendingMoviesResults = MoviesAPI.retrofitService.getTrendingMovies(Constants.TMDB_API_KEY)
//                deleteAllTrendingMovies()
                insertTrendingMovies(trendingMoviesResults.results)
                _status.postValue(LoadStatus.DONE)
            } catch (exception: Exception) {
                print(exception.localizedMessage)
                _status.postValue(LoadStatus.ERROR)
            }
        }
    }
}