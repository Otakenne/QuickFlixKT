package com.example.quickflixkt.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.quickflixkt.dao.SimilarMoviesDao
import com.example.quickflixkt.models.SimilarMovies
import com.example.quickflixkt.models.SimilarMoviesResults
import com.example.quickflixkt.network.MoviesAPI
import com.example.quickflixkt.utility.Constants
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class SimilarMoviesRepository(
    private val movieID: Int,
    private val similarMoviesDao: SimilarMoviesDao
) {
    var similarMovies = similarMoviesDao.getSimilarMovies(movieID).asLiveData()

    private val _status = MutableLiveData(LoadStatus.LOADING)
    val status: LiveData<LoadStatus> = _status

    private suspend fun deleteAllSimilarMovies(movieID: Int) {
        return withContext(Dispatchers.IO) {
            similarMoviesDao.deleteAllSimilarMovies(movieID)
        }
    }

    private suspend fun insertSimilarMovies(similarMovies: List<SimilarMovies>) {
        return withContext(Dispatchers.IO) {
            similarMoviesDao.insertSimilarMovies(similarMovies)
        }
    }

    suspend fun getSimilarMovies() {
        return withContext(Dispatchers.IO) {
            _status.postValue(LoadStatus.LOADING)
            try {
                val similarMoviesResults = MoviesAPI.retrofitService.getSimilarMovies(movieID.toString(), Constants.TMDB_API_KEY)
                if (similarMoviesResults.results != null) {
//                    deleteAllSimilarMovies(movieID)
                    for (similarMovie in similarMoviesResults.results) {
                        similarMovie.original_movie_id = movieID.toString()
                    }
                    insertSimilarMovies(similarMoviesResults.results)
                    _status.postValue(LoadStatus.DONE)
                }
            } catch (exception: Exception) {
                print(exception.localizedMessage)
                _status.postValue(LoadStatus.ERROR)
            }
        }
    }
}