package com.example.quickflixkt.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quickflixkt.models.SearchedMovies
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchedMoviesDao {
    @Insert
    suspend fun insertSearchedMovies(searchedMovies: SearchedMovies)

    @Insert
    suspend fun insertSearchedMovies(searchedMovies: List<SearchedMovies>)

    @Delete
    suspend fun deleteSearchedMovies(searchedMovies: SearchedMovies)

    @Query("DELETE FROM searched_movies_table")
    fun deleteAllSearchedMovies()

    @Query("SELECT * FROM searched_movies_table WHERE search_query = :search_query")
    fun getSearchedMovies(search_query: String): Flow<List<SearchedMovies>>
}