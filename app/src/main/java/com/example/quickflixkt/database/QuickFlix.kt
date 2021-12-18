package com.example.quickflixkt.database

import android.app.Application

class QuickFlix : Application() {
    val database: MoviesDatabase by lazy {
        MoviesDatabase.getDatabase(this)
    }
}
