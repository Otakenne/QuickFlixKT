package com.example.quickflixkt.network

import com.example.quickflixkt.interfaces.APIInterface
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val TMDB_API_KEY = "e479dcf3380507fb7c8c98e03dc0bbde"
private const val DATABASE_NAME = "movies_database"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("")
    .build()

object MoviesAPI {
    val retrofitService: APIInterface by lazy {
        retrofit.create(APIInterface::class.java)
    }
}