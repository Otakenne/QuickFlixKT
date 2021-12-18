package com.example.quickflixkt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quickflixkt.R

class EmptyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}