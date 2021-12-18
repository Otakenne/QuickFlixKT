package com.example.quickflixkt.viewmodels

import androidx.lifecycle.*
import com.example.quickflixkt.repositories.UpcomingMoviesRepository
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.launch

class UpcomingMoviesViewModel(private val upcomingMoviesRepository: UpcomingMoviesRepository): ViewModel() {
    var upcomingMovies = upcomingMoviesRepository.upcomingMovies

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus> = _status

    init {
        getUpcomingMovies()
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            _status.value = LoadStatus.LOADING
            try {
                upcomingMoviesRepository.refreshUpcomingMovies()
                _status.value = LoadStatus.DONE
            } catch (exception: Exception) {
                _status.value = LoadStatus.ERROR
            }
        }
    }
}

class UpcomingMoviesViewModelFactory(private val upcomingMoviesRepository: UpcomingMoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpcomingMoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UpcomingMoviesViewModel(upcomingMoviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}