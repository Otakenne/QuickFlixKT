package com.example.quickflixkt.viewmodels

import androidx.lifecycle.*
import com.example.quickflixkt.repositories.UpcomingMoviesRepository
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.launch

class UpcomingMoviesViewModel(
    private val upcomingMoviesRepository: UpcomingMoviesRepository
    ):
    ViewModel() {

    var upcomingMovies = upcomingMoviesRepository.upcomingMovies
    var status = upcomingMoviesRepository.status

    init {
        getUpcomingMovies()
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            upcomingMoviesRepository.refreshUpcomingMovies()
        }
    }
}

class UpcomingMoviesViewModelFactory(private val upcomingMoviesRepository: UpcomingMoviesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpcomingMoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UpcomingMoviesViewModel(upcomingMoviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}