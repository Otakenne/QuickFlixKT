package com.example.quickflixkt.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quickflixkt.models.Movie
import kotlinx.coroutines.flow.Flow


interface MovieDao {
    @Insert
    suspend fun insertMovie(movie: Movie)

    @Insert
    suspend fun insertMovies(movies: List<Movie>)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("DELETE FROM movie_table")
    fun deleteAllMovies()

    @Query("SELECT * FROM movie_table WHERE id = :id")
    fun getMovie(id: String): Flow<Movie>

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): Flow<List<Movie>>
}