package com.example.quickflixkt.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.quickflixkt.models.UpcomingMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingMoviesDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertUpcomingMovies(upcomingMovie: UpcomingMovie)

    @Insert(onConflict = REPLACE)
    suspend fun insertUpcomingMovie(upcomingMovies: List<UpcomingMovie>)

    @Delete
    suspend fun deleteUpcomingMovie(upcomingMovie: UpcomingMovie)

    @Query("DELETE FROM upcoming_movies_table")
    suspend fun deleteAllUpcomingMovies()

    @Query("SELECT * FROM upcoming_movies_table")
    fun getAllUpcomingMovies(): Flow<List<UpcomingMovie>>
}