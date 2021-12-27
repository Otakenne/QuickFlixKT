package com.example.quickflixkt.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.quickflixkt.dao.ActorMoviesDao
import com.example.quickflixkt.models.ActorMovie
import com.example.quickflixkt.network.MoviesAPI
import com.example.quickflixkt.utility.Constants
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ActorMoviesRepository(
    private val actorID: Int,
    private val actorMoviesDao: ActorMoviesDao
) {
    var actorMovies = actorMoviesDao.getActorMovies(actorID).asLiveData()

    private val _status = MutableLiveData(LoadStatus.LOADING)
    val status: LiveData<LoadStatus> = _status

    private suspend fun deleteActorMovies(actorID: Int) {
        return withContext(Dispatchers.IO) {
            actorMoviesDao.deleteActorMovies(actorID)
        }
    }

    private suspend fun insertActorMovies(actorMovies: List<ActorMovie>) {
        return withContext(Dispatchers.IO) {
            actorMoviesDao.insertActorMovies(actorMovies)
        }
    }

    suspend fun getActorMovies() {
        return withContext(Dispatchers.IO) {
            _status.postValue(LoadStatus.LOADING)
            try {
                val actorMoviesResults = MoviesAPI.retrofitService.getActorCredit(actorID.toString(), Constants.TMDB_API_KEY)
//                deleteActorMovies(actorID)
                for (cast in actorMoviesResults.cast) {
                    cast.actor_id = actorID
                }
                insertActorMovies(actorMoviesResults.cast)
                actorMovies = actorMoviesDao.getActorMovies(actorID).asLiveData()
                _status.postValue(LoadStatus.DONE)
            } catch (exception: Exception) {
                print(exception.localizedMessage)
                _status.postValue(LoadStatus.ERROR)
            }
        }
    }
}