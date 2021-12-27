package com.example.quickflixkt.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.quickflixkt.models.SimilarMovies
import kotlinx.coroutines.flow.Flow

@Dao
interface SimilarMoviesDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertSimilarMovies(similarMovies: SimilarMovies)

    @Insert(onConflict = REPLACE)
    suspend fun insertSimilarMovies(similarMovies: List<SimilarMovies>)

    @Delete
    suspend fun deleteSimilarMovie(similarMovies: SimilarMovies)

    @Query("DELETE FROM similar_movies_table")
    suspend fun deleteAllSimilarMovies()

    @Query("DELETE FROM similar_movies_table WHERE original_movie_id = :originalMovieID")
    suspend fun deleteAllSimilarMovies(originalMovieID: Int)

    @Query("SELECT * FROM similar_movies_table WHERE original_movie_id = :original_movie_id")
    fun getSimilarMovies(original_movie_id: Int): Flow<List<SimilarMovies>>
}