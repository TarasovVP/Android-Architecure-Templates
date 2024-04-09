package com.vnteam.cleanarchitecturedemo.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vnteam.cleanarchitecturedemo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val SERVER_TIMEOUT = 50L
    }
}