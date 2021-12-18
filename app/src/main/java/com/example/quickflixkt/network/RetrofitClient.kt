package com.example.quickflixkt.network

import com.example.quickflixkt.interfaces.APIInterface
import com.example.quickflixkt.utility.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

object MoviesAPI {
    val retrofitService: APIInterface by lazy {
        retrofit.create(APIInterface::class.java)
    }
}