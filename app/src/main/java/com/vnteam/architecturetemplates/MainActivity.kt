package com.vnteam.architecturetemplates

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
import com.vnteam.architecturetemplates.list.ListFragment

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