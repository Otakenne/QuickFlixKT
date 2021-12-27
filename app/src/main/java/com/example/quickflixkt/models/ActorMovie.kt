package com.example.quickflixkt.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actor_movies_table")
data class ActorMovie(
    @PrimaryKey
    val id: Int,
    var actor_id: Int?,
    val title: String?,
    val original_title: String?,
    val character: String?,
    val backdrop_path: String?,
    val poster_path: String?
)
