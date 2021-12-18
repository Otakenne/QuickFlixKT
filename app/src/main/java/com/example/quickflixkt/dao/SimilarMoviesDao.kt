package com.example.quickflixkt.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quickflixkt.models.SimilarMovies
import kotlinx.coroutines.flow.Flow

@Dao
interface SimilarMoviesDao {
    @Insert
    suspend fun insertSimilarMovies(similarMovies: SimilarMovies)

    @Insert
    suspend fun insertSimilarMovies(similarMovies: List<SimilarMovies>)

    @Delete
    suspend fun deleteSimilarMovie(similarMovies: SimilarMovies)

    @Query("DELETE FROM similar_movies_table")
    fun deleteAllSimilarMovies()

    @Query("SELECT * FROM similar_movies_table WHERE original_movie_id = :original_movie_id")
    fun getSimilarMovies(original_movie_id: String): Flow<List<SimilarMovies>>
}