package com.example.quickflixkt.viewmodels

import androidx.lifecycle.*
import com.example.quickflixkt.repositories.ActorMoviesRepository
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.launch

class ActorMoviesViewModel(private val actorMoviesRepository: ActorMoviesRepository): ViewModel() {
    var actorMovies = actorMoviesRepository.actorMovies
    var status = actorMoviesRepository.status

    init {
        getActorMovies()
    }

    private fun getActorMovies() {
        viewModelScope.launch {
            actorMoviesRepository.getActorMovies()
        }
    }
}

class ActorMoviesViewModelFactory(private val actorMoviesRepository: ActorMoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActorMoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ActorMoviesViewModel(actorMoviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}