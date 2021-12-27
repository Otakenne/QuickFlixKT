package com.example.quickflixkt.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.quickflixkt.dao.MovieCreditDao
import com.example.quickflixkt.models.MovieCredit
import com.example.quickflixkt.network.MoviesAPI
import com.example.quickflixkt.utility.Constants
import com.example.quickflixkt.utility.LoadStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MovieCreditsRepository(
    private val movieID: Int,
    private val movieCreditDao: MovieCreditDao
) {
    var movieCredits = movieCreditDao.getMovieCredit(movieID).asLiveData()

    private val _status = MutableLiveData(LoadStatus.LOADING)
    val status: LiveData<LoadStatus> = _status

    private suspend fun deleteAllMovieCredits(movieID: Int) {
        return withContext(Dispatchers.IO) {
            movieCreditDao.deleteAllMovieCredit(movieID)
        }
    }

    private suspend fun insertMovieCredits(movieCredits: List<MovieCredit>) {
        return withContext(Dispatchers.IO) {
            movieCreditDao.insertMovieCredits(movieCredits)
        }
    }

    suspend fun getMovieCredits() {
        return withContext(Dispatchers.IO) {
            _status.postValue(LoadStatus.LOADING)
            try {
                val movieCreditResults = MoviesAPI.retrofitService.getMovieCredits(movieID.toString(), Constants.TMDB_API_KEY)
//                deleteAllMovieCredits(movieID)
                for (movieCredit in movieCreditResults.cast) {
                    movieCredit.movie_id = movieID
                }
                insertMovieCredits(movieCreditResults.cast)
                _status.postValue(LoadStatus.DONE)
            } catch (exception: Exception) {
                print(exception.localizedMessage)
                _status.postValue(LoadStatus.ERROR)
            }
        }
    }
}