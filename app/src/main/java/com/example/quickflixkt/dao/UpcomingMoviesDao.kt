package com.example.quickflixkt.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quickflixkt.models.UpcomingMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingMoviesDao {
    @Insert
    suspend fun insertUpcomingMovies(upcomingMovie: UpcomingMovie)

    @Insert
    suspend fun insertUpcomingMovie(upcomingMovies: List<UpcomingMovie>)

    @Delete
    suspend fun deleteUpcomingMovie(upcomingMovie: UpcomingMovie)

    @Query("DELETE FROM upcoming_movies_table")
    fun deleteAllUpcomingMovies()

    @Query("SELECT * FROM upcoming_movies_table")
    fun getAllUpcomingMovies(): Flow<List<UpcomingMovie>>
}