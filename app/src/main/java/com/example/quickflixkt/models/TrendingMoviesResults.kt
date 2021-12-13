package com.example.quickflixkt.models

data class TrendingMoviesResults(
    val page: Int,
    val results: List<TrendingMovie>
)
