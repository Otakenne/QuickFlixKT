package com.example.quickflixkt.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.quickflixkt.models.Actor
import kotlinx.coroutines.flow.Flow

@Dao
interface ActorDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertActor(actor: Actor)

    @Insert(onConflict = REPLACE)
    suspend fun insertActors(actors: List<Actor>)

    @Update
    suspend fun updateActor(actor: Actor)

    @Delete
    suspend fun deleteActor(actor: Actor)

    @Query("DELETE FROM actor_table WHERE id = :actorID")
    suspend fun deleteActor(actorID: Int)

    @Query("DELETE FROM actor_table")
    suspend fun deleteAllActors()

    @Query("SELECT * FROM actor_table WHERE id = :id")
    fun getActor(id: Int): Flow<Actor>

    @Query("SELECT * FROM actor_table")
    fun getAllActors(): Flow<List<Actor>>
}