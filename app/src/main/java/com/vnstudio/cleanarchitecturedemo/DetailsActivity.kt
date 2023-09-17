package com.vnstudio.cleanarchitecturedemo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vnstudio.cleanarchitecturedemo.MainActivity.Companion.FORK

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val fork = intent.getSerializableExtra(FORK) as? Fork
        findViewById<TextView>(R.id.forkName).text = fork?.full_name
        findViewById<TextView>(R.id.ownerName).text = fork?.owner?.login
        findViewById<TextView>(R.id.forkDescription).text = fork?.full_name
        setOwnerAvatar(fork)
        findViewById<Button>(R.id.backButton).setOnClickListener {
            onBackPressed()
        }
    }

    private fun setOwnerAvatar(fork: Fork?) {
        val ownerAvatar = findViewById<ImageView>(R.id.ownerAvatar)
        val httpUrlConnector = HttpUrlConnector()
        httpUrlConnector.getBitmapFromHttpUrlUrl(fork?.owner?.avatar_url, { imageFromUrl ->
            ownerAvatar.setImageBitmap(imageFromUrl)
        }, { errorText ->
            Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
        })
    }
}