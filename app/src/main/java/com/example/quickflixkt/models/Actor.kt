package com.example.quickflixkt.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actor_table")
data class Actor(
    @PrimaryKey
    @NonNull
    val id: Int,
    val gender: Int,
    val name: String,
    val biography: String,
    val birthday: String,
    val profile_path: String,
)
