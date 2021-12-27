package com.example.quickflixkt.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_credit_table")
data class MovieCredit(
    @PrimaryKey()
    val id: Int,
    val character: String?,
    val name: String?,
    val original_name: String?,
    val profile_path: String?,
    var movie_id: Int?
)
