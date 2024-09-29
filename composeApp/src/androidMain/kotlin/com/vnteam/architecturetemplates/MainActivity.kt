package com.vnteam.architecturetemplates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vnteam.architecturetemplates.presentation.viewmodels.withViewModelStoreOwner
import presentation.AppNavigation
import theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            withViewModelStoreOwner {
                AppTheme {
                    AppNavigation()
                }
            }
        }
    }
}