package com.example.quickflixkt.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "similar_movies_table")
data class SimilarMovies(
    @PrimaryKey
    @NonNull
    val id: Int,
    var original_movie_id: String?,
    val imdb_id: String?,
    val original_title: String?,
    val title: String?,
    val overview: String?,
    val status: String?,
    val vote_average: Double?,
    val backdrop_path: String?,
    val poster_path: String?
)
