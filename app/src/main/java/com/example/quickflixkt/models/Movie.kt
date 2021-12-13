package com.example.quickflixkt.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey
    @NonNull
    val id: Int,
    val imdb_id: String,
    val original_title: String,
    val title: String,
    val overview: String,
    val status: String,
    val vote_average: Double,
    val backdrop_path: String
)
