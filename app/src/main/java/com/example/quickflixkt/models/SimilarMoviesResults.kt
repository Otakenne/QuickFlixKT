package com.example.quickflixkt.models

data class SimilarMoviesResults(
    val page: Int,
    val results: List<SimilarMovies>?
)
