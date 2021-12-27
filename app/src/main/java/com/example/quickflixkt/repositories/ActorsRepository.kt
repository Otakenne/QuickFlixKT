package com.example.quickflixkt.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.quickflixkt.dao.ActorDao
import com.example.quickflixkt.models.Actor
import com.example.quickflixkt.models.Movie
import com.example.quickflixkt.network.MoviesAPI
import com.example.quickflixkt.utility.Constants
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ActorsRepository(private val actorID: Int, private val actorDao: ActorDao) {
    var actor = actorDao.getActor(actorID).asLiveData()

    private val _status = MutableLiveData(LoadStatus.LOADING)
    val status: LiveData<LoadStatus> = _status

    private suspend fun deleteActor(actorID: Int) {
        return withContext(Dispatchers.IO) {
            actorDao.deleteActor(actorID)
        }
    }

    private suspend fun insertActor(actor: Actor) {
        return withContext(Dispatchers.IO) {
            actorDao.insertActor(actor)
        }
    }

    suspend fun getActor() {
        return withContext(Dispatchers.IO) {
            _status.postValue(LoadStatus.LOADING)
            try {
                val tmdbActor = MoviesAPI.retrofitService.getActor(actorID.toString(), Constants.TMDB_API_KEY)
//                deleteActor(actorID)
                insertActor(tmdbActor)
                _status.postValue(LoadStatus.DONE)
            } catch (exception: Exception) {
                print(exception.localizedMessage)
                _status.postValue(LoadStatus.ERROR)
            }
        }
    }
}