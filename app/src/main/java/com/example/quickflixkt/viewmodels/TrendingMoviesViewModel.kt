package com.example.quickflixkt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quickflixkt.repositories.TrendingMoviesRepository
import kotlinx.coroutines.launch

class TrendingMoviesViewModel(
        private val trendingMoviesRepository: TrendingMoviesRepository
    ): ViewModel() {

    var trendingMovies = trendingMoviesRepository.trendingMovies
    var status = trendingMoviesRepository.status

    init {
        getTrendingMovies()
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            trendingMoviesRepository.getTrendingMovies()
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