package presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import com.vnteam.architecturetemplates.Res
import com.vnteam.architecturetemplates.ic_dark_mode
import com.vnteam.architecturetemplates.ic_light_mode
import com.vnteam.architecturetemplates.presentation.resources.LocalStringResources
import com.vnteam.architecturetemplates.presentation.resources.StringResources
import com.vnteam.architecturetemplates.presentation.resources.getStringResourcesByLocale
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import theme.AppTheme

@Composable
fun App() {
    val screenState = remember { mutableStateOf(ScreenState()) }
    val isDarkTheme = remember { mutableStateOf(screenState.value.isDarkTheme) }
    val language = remember { mutableStateOf(Locale.current.language) }
    CompositionLocalProvider(LocalStringResources provides getStringResourcesByLocale(language.value)) {
        AppTheme(isDarkTheme.value) {
            ScaffoldContent(screenState, language, isDarkTheme)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldContent(screenState: MutableState<ScreenState>, language: MutableState<String>, isDarkTheme: MutableState<Boolean>) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    if (screenState.value.snackbarVisible) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = screenState.value.snackbarMessage,
                duration = SnackbarDuration.Short,
            )
            screenState.value = screenState.value.copy(snackbarVisible = false)
        }
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
                                contentDescription = LocalStringResources.current.BACK
                            )
                        }
                    }
                },
                actions = {
                    if (!screenState.value.topAppBarActionVisible) {
                        IconButton(onClick = {
                            language.value = if (language.value == "en") "uk" else "en"
                        }) {
                            Text(language.value, color = Color.White)
                        }
                        IconButton(onClick = {
                            isDarkTheme.value = !isDarkTheme.value
                        }) {
                            Icon(
                                painter = painterResource(if (isDarkTheme.value) Res.drawable.ic_dark_mode else Res.drawable.ic_light_mode),
                                contentDescription = if (isDarkTheme.value) "Switch to Light Theme" else "Switch to Dark Theme",
                                tint = Color.White
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