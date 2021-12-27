package com.example.quickflixkt.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.quickflixkt.models.ActorMovie
import com.example.quickflixkt.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface ActorMoviesDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertActorMovie(actorMovie: ActorMovie)

    @Insert(onConflict = REPLACE)
    suspend fun insertActorMovies(actorMovies: List<ActorMovie>)

    @Delete
    suspend fun deleteActorMovie(actorMovie: ActorMovie)

    @Query("DELETE FROM actor_movies_table WHERE actor_id = :actorID")
    suspend fun deleteActorMovies(actorID: Int)

    @Query("DELETE FROM actor_movies_table")
    suspend fun deleteAllActorMovies()

    @Query("SELECT * FROM actor_movies_table WHERE actor_id = :actor_id")
    fun getActorMovies(actor_id: Int): Flow<List<ActorMovie>>
}