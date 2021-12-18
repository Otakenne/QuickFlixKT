package com.example.quickflixkt.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending_movies_table")
data class TrendingMovie(
    @PrimaryKey
    @NonNull
    val id: Int,
    val original_title: String,
    val title: String,
    val overview: String,
//    val status: String,
    val vote_average: Double,
    val poster_path: String,
    val backdrop_path: String
)


