package com.example.quickflixkt.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.quickflixkt.models.Actor
import kotlinx.coroutines.flow.Flow

interface ActorDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertActor(actor: Actor)

    @Insert(onConflict = REPLACE)
    suspend fun insertActors(actors: List<Actor>)

    @Update
    suspend fun updateActor(actor: Actor)

    @Delete
    suspend fun deleteActor(actor: Actor)

    @Query("DELETE FROM actor_table")
    fun deleteAllActors(actor: Actor)

    @Query("SELECT * FROM actor_table WHERE id = :id")
    fun getActor(id: String): Flow<Actor>

    @Query("SELECT * FROM actor_table")
    fun getAllActors(): Flow<List<Actor>>
}