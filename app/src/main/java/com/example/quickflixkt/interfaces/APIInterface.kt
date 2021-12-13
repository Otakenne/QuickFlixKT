package com.example.quickflixkt.interfaces

import androidx.lifecycle.LiveData
import com.example.quickflixkt.models.*
import retrofit2.http.GET
import retrofit2.http.Query

import retrofit2.http.Path


interface APIInterface {
    @GET("movie/popular?api_key={api_key}")
    suspend fun getTrendingMovies(
        @Query("api_key") api_key: String
    ): TrendingMoviesResults

    @GET("movie/upcoming?api_key={api_key}")
    suspend fun getUpcomingMovies(
        @Query("api_key") api_key: String?
    ): UpcomingMoviesResults

    @GET("movie/{movie_id}?api_key={api_key}")
    suspend fun getMovie(
        @Query("api_key") api_key: String?,
        @Path("movie_id") movie_id: String?
    ): Movie

    @GET("movie/{movie_id}/credits?api_key={api_key}")
    suspend fun getMovieCredits(
        @Query("api_key") api_key: String?,
        @Path("movie_id") movie_id: String?
    ): MovieCreditResults

    @GET("movie/{movie_id}/similar?api_key={api_key}")
    suspend fun getSimilarMovies(
        @Query("api_key") api_key: String?,
        @Path("movie_id") movie_id: String?
    ): MovieCreditResults

    @GET("person/{person_id}?api_key={api_key}")
    suspend fun getActor(
        @Query("api_key") api_key: String?,
        @Path("person_id") person_id: String?
    ): Actor

    @GET("person/{person_id}/movie_credits?api_key={api_key}")
    suspend fun getActorCredit(
        @Query("api_key") api_key: String?,
        @Path("person_id") person_id: String?
    ): ActorMoviesResults

    @GET("search/movie?api_key={api_key}&query={query}")
    suspend fun getMovieSearchResults(
        @Query("api_key") api_key: String?,
        @Query("query") query: String?
    ): SearchedMoviesResults
}