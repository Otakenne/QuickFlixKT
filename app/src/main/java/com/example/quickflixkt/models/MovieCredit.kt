package com.example.quickflixkt.models

import androidx.room.Entity

@Entity(tableName = "movie_credit_table")
data class MovieCredit(
    val character: String,
    val name: String,
    val original_name: String,
    val profile_path: String,
    val movie_id: Int
)
