package com.example.quickflixkt.interfaces

import androidx.lifecycle.LiveData
import com.example.quickflixkt.models.*
import retrofit2.http.GET
import retrofit2.http.Query

import retrofit2.http.Path


interface APIInterface {
    @GET("movie/now_playing")
    suspend fun getTrendingMovies(
        @Query("api_key") api_key: String
    ): TrendingMoviesResults

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") api_key: String
    ): UpcomingMoviesResults

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String
    ): Movie

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String
    ): MovieCreditResults

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String
    ): SimilarMoviesResults

    @GET("person/{person_id}")
    suspend fun getActor(
        @Path("person_id") person_id: String,
        @Query("api_key") api_key: String
    ): Actor

    @GET("person/{person_id}/movie_credits")
    suspend fun getActorCredit(
        @Path("person_id") person_id: String,
        @Query("api_key") api_key: String
    ): ActorMoviesResults

    @GET("search/movie")
    suspend fun getMovieSearchResults(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): SearchedMoviesResults
}