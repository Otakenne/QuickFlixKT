package com.example.quickflixkt.viewmodels

import androidx.lifecycle.*
import com.example.quickflixkt.dao.TrendingMoviesDao
import com.example.quickflixkt.models.TrendingMovie
import com.example.quickflixkt.network.MoviesAPI
import com.example.quickflixkt.utility.Constants
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.launch

class TrendingMoviesViewModel(private val trendingMoviesDao: TrendingMoviesDao): ViewModel() {
    val trendingMoviesRoom: LiveData<List<TrendingMovie>> = trendingMoviesDao.getAllTrendingMovies().asLiveData()

    private val _status = MutableLiveData<LoadStatus>()
    var status: LiveData<LoadStatus> = _status

    private val _trendingMovies = MutableLiveData<List<TrendingMovie>>()
    var trendingMovies: LiveData<List<TrendingMovie>> = _trendingMovies

    private fun deleteAllTrendingMovies() {
        viewModelScope.launch {
            trendingMoviesDao.deleteAllTrendingMovies()
        }
    }

    private fun insertTrendingMovies(trendingMovies: List<TrendingMovie>) {
        viewModelScope.launch {
            trendingMoviesDao.insertTrendingMovies(trendingMovies)
        }
    }

    init {
        getTrendingMovies()
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            _status.value = LoadStatus.LOADING
            try {
                val trendingMoviesResults = MoviesAPI.retrofitService.getTrendingMovies(Constants.TMDB_API_KEY)
                _trendingMovies.value = trendingMoviesResults.results
                _status.value = LoadStatus.DONE

                deleteAllTrendingMovies()
                insertTrendingMovies(trendingMovies.value ?: listOf())
            } catch (exception: Exception) {
                _trendingMovies.value = listOf()
                _status.value = LoadStatus.ERROR
            }
        }
    }
}