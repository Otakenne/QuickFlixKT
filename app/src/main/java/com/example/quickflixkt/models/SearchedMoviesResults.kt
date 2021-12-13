package com.example.quickflixkt.models

data class SearchedMoviesResults(
    val page: Int,
    val results: List<SearchedMovies>
)
