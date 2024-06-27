package presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val scope = rememberCoroutineScope()
    val screenState = remember { mutableStateOf(ScreenState()) }
    val snackbarHostState = remember { SnackbarHostState() }
    if (screenState.value.snackbarVisible) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = screenState.value.snackbarMessage,
                duration = SnackbarDuration.Short,)
            screenState.value = screenState.value.copy(snackbarVisible = false, snackbarMessage = "")
        }
        screenState.value = screenState.value.copy(snackbarVisible = false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(screenState.value.topAppBarTitle) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    if (screenState.value.topAppBarActionVisible) {
                        IconButton(onClick = screenState.value.topAppBarAction) {
                            Icon(
                                tint = Color.White,
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = getStringResources().BACK
                            )
                        }
                    }
                })
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    actionColor = Color.White,
                    containerColor = if (screenState.value.isSnackbarError) Color.Red else Color.Green
                )
            }
        },
        floatingActionButton = {
            if (screenState.value.floatingActionButtonVisible) {
                FloatingActionButton(
                    onClick = { screenState.value.floatingActionButtonAction() },
                    content = { Text(screenState.value.floatingActionButtonTitle) },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            }
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                AppNavigation(screenState)
                if (screenState.value.isProgressVisible) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )
}