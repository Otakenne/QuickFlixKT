package com.example.quickflixkt.viewmodels

import androidx.lifecycle.*
import com.example.quickflixkt.repositories.SimilarMoviesRepository
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.launch

class SimilarMoviesViewModel(private val similarMoviesRepository: SimilarMoviesRepository): ViewModel() {

    var similarMovies = similarMoviesRepository.similarMovies
    var status = similarMoviesRepository.status

    init {
        getMovieCredits()
    }

    private fun getMovieCredits() {
        viewModelScope.launch {
            similarMoviesRepository.getSimilarMovies()
        }
    }
}

class SimilarMoviesViewModelFactory(private val similarMoviesRepository: SimilarMoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SimilarMoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SimilarMoviesViewModel(similarMoviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}