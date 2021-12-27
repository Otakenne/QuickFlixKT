package com.example.quickflixkt.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.quickflixkt.models.TrendingMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingMoviesDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertTrendingMovie(trendingMovie: TrendingMovie)

    @Insert(onConflict = REPLACE)
    suspend fun insertTrendingMovies(trendingMovies: List<TrendingMovie>)

    @Delete
    suspend fun deleteTrendingMovie(trendingMovie: TrendingMovie)

    @Query("DELETE FROM trending_movies_table")
    suspend fun deleteAllTrendingMovies()

    @Query("SELECT * FROM trending_movies_table")
    fun getAllTrendingMovies(): Flow<List<TrendingMovie>>

//    @Query("SELECT EXISTS(SELECT * FROM trending_movies_table)")
//    suspend fun trendingMoviesExists(): Int
}