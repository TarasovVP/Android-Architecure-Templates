<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/presentation/MainActivity.kt
package com.vnteam.architecturetemplates.presentation
========
package com.vnteam.architecturetemplates
>>>>>>>> 13d1264 (Rename project):app/src/main/java/com/vnteam/architecturetemplates/MainActivity.kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vnteam.architecturetemplates.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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