package com.example.quickflixkt.viewmodels

import androidx.lifecycle.*
import com.example.quickflixkt.repositories.ActorMoviesRepository
import com.example.quickflixkt.repositories.ActorsRepository
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.launch

class ActorsViewModel(private val actorsRepository: ActorsRepository): ViewModel() {
    var actor = actorsRepository.actor
    var status = actorsRepository.status

    init {
        getActor()
    }

    private fun getActor() {
        viewModelScope.launch {
            actorsRepository.getActor()
        }
    }
}

class ActorsViewModelFactory(private val actorsRepository: ActorsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActorsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ActorsViewModel(actorsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}