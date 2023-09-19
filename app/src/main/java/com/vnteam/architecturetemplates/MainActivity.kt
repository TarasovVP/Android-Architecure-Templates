package com.vnstudio.cleanarchitecturedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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
        const val DATABASE_NAME = "CleanArchitectureDemo"
    }
}