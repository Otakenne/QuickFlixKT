package com.example.quickflixkt.models

import androidx.room.Entity

@Entity(tableName = "actor_movies_table")
data class ActorMovie(
    val id: Int,
    val actor_id: Int,
    val title: String,
    val original_title: String,
    val character: String,
    val backdrop_path: String
)
