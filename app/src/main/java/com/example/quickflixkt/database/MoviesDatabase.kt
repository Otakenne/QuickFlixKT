package com.example.quickflixkt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quickflixkt.dao.*
import com.example.quickflixkt.models.*

@Database(
    entities = [Actor::class,
        ActorMovie::class,
        Movie::class,
        MovieCredit::class,
        SearchedMovies::class,
        SimilarMovies::class,
        TrendingMovie::class,
        UpcomingMovie::class], version = 1, exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun actorDao(): ActorDao
    abstract fun actorMoviesDao(): ActorsMoviesDao
    abstract fun movieCreditDao(): MovieCreditDao
    abstract fun movieDao(): MovieDao
    abstract fun searchedMoviesDao(): SearchedMoviesDao
    abstract fun similarMoviesDao(): SimilarMoviesDao
    abstract fun trendingMoviesDao(): TrendingMoviesDao
    abstract fun upcomingMoviesDao(): UpcomingMoviesDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null
        fun getDatabase(context: Context): MoviesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "movies_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
