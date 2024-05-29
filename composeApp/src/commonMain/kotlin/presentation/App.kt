package presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.vnteam.architecturetemplates.presentation.resources.getStringResources

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val scaffoldState = mutableStateOf(ScaffoldState())
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(scaffoldState.value.topAppBarTitle) },
                navigationIcon = {
                    if (scaffoldState.value.topAppBarActionVisible) {
                        IconButton(onClick = scaffoldState.value.topAppBarAction) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = getStringResources().BACK
                            )
                        }
                    }
                })
        },
        floatingActionButton = {
            if (scaffoldState.value.floatingActionButtonVisible) {
                FloatingActionButton(
                    onClick = { scaffoldState.value.floatingActionButtonAction() },
                    content = { Text(scaffoldState.value.floatingActionButtonTitle) }
                )
            }
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                AppNavigation(scaffoldState)
            }
        }
    )
}