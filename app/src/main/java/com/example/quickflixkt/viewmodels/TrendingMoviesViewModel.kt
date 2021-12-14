package com.example.quickflixkt.viewmodels

import androidx.lifecycle.*
import com.example.quickflixkt.dao.TrendingMoviesDao
import com.example.quickflixkt.models.TrendingMovie
import com.example.quickflixkt.network.MoviesAPI
import com.example.quickflixkt.repositories.TrendingMoviesRepository
import com.example.quickflixkt.utility.Constants
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.launch

class TrendingMoviesViewModel(
        private val trendingMoviesRepository: TrendingMoviesRepository
    ): ViewModel() {
    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus> = _status

    private val _trendingMovies = MutableLiveData<List<TrendingMovie>>()
    val trendingMovies: LiveData<List<TrendingMovie>> = _trendingMovies

    init {
        getTrendingMovies()
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            _status.value = LoadStatus.LOADING
            try {
                _trendingMovies.value = trendingMoviesRepository.getTrendingMovies().value
                _status.value = LoadStatus.DONE
            } catch (exception: Exception) {
                _trendingMovies.value = listOf()
                _status.value = LoadStatus.ERROR
            }
        }
    }
}


class TrendingMoviesViewModelFactory(private val trendingMoviesRepository: TrendingMoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrendingMoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrendingMoviesViewModel(trendingMoviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}