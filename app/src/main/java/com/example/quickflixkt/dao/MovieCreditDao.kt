package com.example.quickflixkt.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quickflixkt.models.MovieCredit
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCreditDao {
    @Insert
    suspend fun insertMovieCredit(movieCredit: MovieCredit)

    @Insert
    suspend fun insertMovieCredits(movieCredits: List<MovieCredit>)

    @Delete
    suspend fun deleteMovieCredit(movieCredit: MovieCredit)

    @Query("DELETE FROM movie_credit_table")
    fun deleteAllMovieCredit()

    @Query("SELECT * FROM movie_credit_table WHERE movie_id = :movie_id")
    fun getMovieCredit(movie_id: String): Flow<List<MovieCredit>>
}