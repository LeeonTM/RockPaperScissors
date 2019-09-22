package com.example.rockpaperscissors.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.models.Game

class MainActivity : AppCompatActivity() {

    private var games = arrayListOf<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
