package com.vnteam.architecturetemplates

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vnteam.architecturetemplates.list.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, ListFragment.newInstance())
            addToBackStack(null)
            commit()
        }
    }

    companion object {

        const val FORKS_URL = "https://api.github.com/repos/octocat/Spoon-Knife/forks"
        const val SUCCESS_HTTP_CONNECTION = 1
        const val SUCCESS_SQLITE_CONNECTION = 2
        const val ERROR = 3
        const val SUCCESS_IMAGE_FROM_URL_CONNECTION = 4
        const val DATABASE_NAME = "CleanArchitectureDemo"
        const val TABLE_NAME = "forks"
        const val FORK = "fork"
    }
}