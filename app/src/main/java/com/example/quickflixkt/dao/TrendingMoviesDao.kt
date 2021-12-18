package com.example.quickflixkt.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quickflixkt.models.TrendingMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingMoviesDao {
    @Insert
    suspend fun insertTrendingMovie(trendingMovie: TrendingMovie)

    @Insert
    suspend fun insertTrendingMovies(trendingMovies: List<TrendingMovie>)

    @Delete
    suspend fun deleteTrendingMovie(trendingMovie: TrendingMovie)

    @Query("DELETE FROM trending_movies_table")
    fun deleteAllTrendingMovies()

    @Query("SELECT * FROM trending_movies_table")
    fun getAllTrendingMovies(): Flow<List<TrendingMovie>>
}