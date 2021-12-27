package com.example.quickflixkt.viewmodels

import androidx.lifecycle.*
import com.example.quickflixkt.repositories.MoviesRepository
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesRepository: MoviesRepository): ViewModel() {

    var movie = moviesRepository.movie
    var status = moviesRepository.status

    init {
        getMovie()
    }

    private fun getMovie() {
        viewModelScope.launch {
            moviesRepository.getMovie()
        }
    }
}

class MoviesViewModelFactory(private val moviesRepository: MoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel(moviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}