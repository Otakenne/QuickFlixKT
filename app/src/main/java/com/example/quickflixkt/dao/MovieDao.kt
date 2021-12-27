package com.example.quickflixkt.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.quickflixkt.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovies()

    @Query("DELETE FROM movie_table WHERE id = :movieID")
    suspend fun deleteMovie(movieID: Int)

    @Query("SELECT * FROM movie_table WHERE id = :id")
    fun getMovie(id: Int): Flow<Movie>

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): Flow<List<Movie>>
}