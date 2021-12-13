package com.example.quickflixkt.models

import androidx.annotation.NonNull
import androidx.room.PrimaryKey

data class SearchedMovies(
    @PrimaryKey
    @NonNull
    val id: Int,
    val imdb_id: String,
    val original_title: String,
    val title: String,
    val overview: String,
    val status: String,
    val vote_average: Double,
    val backdrop_path: String,
    val search_query: String
)
