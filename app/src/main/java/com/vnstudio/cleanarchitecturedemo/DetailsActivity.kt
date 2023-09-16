package com.vnstudio.cleanarchitecturedemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vnstudio.cleanarchitecturedemo.MainActivity.Companion.FORK

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fork = intent.getSerializableExtra(FORK)
        Log.e("apiTAG", "DetailsActivity onCreate fork $fork")
    }
}