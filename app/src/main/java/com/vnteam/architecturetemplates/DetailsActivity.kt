package com.vnstudio.cleanarchitecturedemo

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.vnstudio.cleanarchitecturedemo.MainActivity.Companion.FORK

class DetailsActivity : AppCompatActivity() {

    @BindView(R.id.forkName)
    lateinit var forkName: TextView

    @BindView(R.id.ownerName)
    lateinit var ownerName: TextView

    @BindView(R.id.forkDescription)
    lateinit var forkDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        ButterKnife.bind(this)
        val fork = intent.getSerializableExtra(FORK) as? Fork
        forkName.text = fork?.fullName
        ownerName.text = fork?.owner?.login
        forkDescription.text = fork?.fullName
        setOwnerAvatar(fork)
    }

    @OnClick(R.id.backButton)
    fun onBackButtonClick() {
        onBackPressed()
    }

    private fun setOwnerAvatar(fork: Fork?) {
        val ownerAvatar = findViewById<ImageView>(R.id.ownerAvatar)
        val httpClientConnector = OkHttpClientConnector()
        httpClientConnector.getBitmapFromHttpUrlUrl(fork?.owner?.avatarUrl, { imageFromUrl ->
            ownerAvatar.setImageBitmap(imageFromUrl)
        }, { errorText ->
            Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
        })
    }
}