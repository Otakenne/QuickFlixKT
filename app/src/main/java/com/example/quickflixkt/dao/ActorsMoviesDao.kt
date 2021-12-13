package com.example.quickflixkt.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quickflixkt.models.ActorMovie
import com.example.quickflixkt.models.Movie
import kotlinx.coroutines.flow.Flow


interface ActorsMoviesDao {
    @Insert
    suspend fun insertActorMovie(actorMovie: ActorMovie)

    @Insert
    suspend fun insertActorMovies(actorMovies: List<ActorMovie>)

    @Delete
    suspend fun deleteActorMovie(actorMovie: ActorMovie)

    @Query("DELETE FROM actor_movies_table")
    fun deleteAllActorMovies()

    @Query("SELECT * FROM actor_movies_table WHERE actor_id = :actor_id")
    fun getActorMovies(actor_id: String): Flow<List<Movie>>
}