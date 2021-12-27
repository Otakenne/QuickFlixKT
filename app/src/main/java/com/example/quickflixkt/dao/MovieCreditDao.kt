package com.example.quickflixkt.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.quickflixkt.models.MovieCredit
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCreditDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertMovieCredit(movieCredit: MovieCredit)

    @Insert(onConflict = REPLACE)
    suspend fun insertMovieCredits(movieCredits: List<MovieCredit>)

    @Delete
    suspend fun deleteMovieCredit(movieCredit: MovieCredit)

    @Query("DELETE FROM movie_credit_table")
    suspend fun deleteAllMovieCredit()

    @Query("DELETE FROM movie_credit_table WHERE movie_id = :movie_id")
    suspend fun deleteAllMovieCredit(movie_id: Int)

    @Query("SELECT * FROM movie_credit_table WHERE movie_id = :movie_id")
    fun getMovieCredit(movie_id: Int): Flow<List<MovieCredit>>
}