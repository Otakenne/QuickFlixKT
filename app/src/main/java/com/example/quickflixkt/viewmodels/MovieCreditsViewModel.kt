package com.example.quickflixkt.viewmodels

import androidx.lifecycle.*
import com.example.quickflixkt.repositories.MovieCreditsRepository
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.launch

class MovieCreditsViewModel(private val movieCreditsRepository: MovieCreditsRepository): ViewModel() {

    var movieCredits = movieCreditsRepository.movieCredits
    var status = movieCreditsRepository.status

    init {
        getMovieCredits()
    }

    private fun getMovieCredits() {
        viewModelScope.launch {
            movieCreditsRepository.getMovieCredits()
        }
    }
}

class MovieCreditsViewModelFactory(private val movieCreditsRepository: MovieCreditsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieCreditsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieCreditsViewModel(movieCreditsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}