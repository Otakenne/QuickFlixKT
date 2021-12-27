package com.example.quickflixkt.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.quickflixkt.models.SearchedMovies
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchedMoviesDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertSearchedMovies(searchedMovies: SearchedMovies)

    @Insert(onConflict = REPLACE)
    suspend fun insertSearchedMovies(searchedMovies: List<SearchedMovies>)

    @Delete
    suspend fun deleteSearchedMovies(searchedMovies: SearchedMovies)

    @Query("DELETE FROM searched_movies_table")
    suspend fun deleteAllSearchedMovies()

    @Query("SELECT * FROM searched_movies_table WHERE search_query = :search_query")
    fun getSearchedMovies(search_query: String): Flow<List<SearchedMovies>>
}