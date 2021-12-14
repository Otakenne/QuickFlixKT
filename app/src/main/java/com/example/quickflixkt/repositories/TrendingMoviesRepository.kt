package com.example.quickflixkt.repositories

import androidx.lifecycle.*
import com.example.quickflixkt.dao.TrendingMoviesDao
import com.example.quickflixkt.models.TrendingMovie
import kotlinx.coroutines.launch

class TrendingMoviesRepository(private val trendingMoviesDao: TrendingMoviesDao) {
    val trendingMovies: LiveData<List<TrendingMovie>> = trendingMoviesDao.getAllTrendingMovies().asLiveData()


}