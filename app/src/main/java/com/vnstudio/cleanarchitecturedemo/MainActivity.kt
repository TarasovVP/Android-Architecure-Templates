package com.vnstudio.cleanarchitecturedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        const val FORKS_URL = "https://api.github.com/repos/octocat/Spoon-Knife/forks"
    }
}