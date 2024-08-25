
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vnteam.architecturetemplates.presentation.resources.LocalStringResources
import com.vnteam.architecturetemplates.presentation.resources.getStringResourcesByLocale
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import com.vnteam.architecturetemplates.presentation.viewmodels.AppViewModel
import kotlinx.browser.window
import org.koin.compose.koinInject
import org.w3c.dom.events.Event
import presentation.components.SplashScreen
import presentation.create.CreateScreen
import presentation.details.DetailsScreen
import presentation.list.ListScreen
import presentation.screens.create.CreateContent
import presentation.screens.details.DetailsContent
import presentation.screens.list.ListContent
import theme.AppTheme

@Composable
fun App(appViewModel: AppViewModel) {
    val isDarkTheme = appViewModel.isDarkTheme.collectAsState()
    val language = appViewModel.language.collectAsState()
    CompositionLocalProvider(LocalStringResources provides getStringResourcesByLocale(language.value.orEmpty())) {
        isDarkTheme.value?.let {
            AppTheme(it) {
                ScaffoldContent(koinInject())
            }
        } ?: SplashScreen()
    }
}

@Composable
fun ScaffoldContent(screenState: MutableState<ScreenState>) {
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(screenState.value.appMessageState.messageVisible) {
        if (screenState.value.appMessageState.messageVisible) {
            snackbarHostState.showSnackbar(
                message = screenState.value.appMessageState.messageText,
                duration = SnackbarDuration.Short,
            )
            screenState.value = screenState.value.copy(appMessageState = screenState.value.appMessageState.copy(messageVisible = false))
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    actionColor = Color.White,
                    containerColor = if (screenState.value.appMessageState.isMessageError) Color.Red else Color.Green
                )
            }
        },
        floatingActionButton = {
            if (screenState.value.floatingActionState.floatingActionButtonVisible) {
                ExtendedFloatingActionButton(
                    onClick = { screenState.value.floatingActionState.floatingActionButtonAction() },
                    content = { Text(screenState.value.floatingActionState.floatingActionButtonTitle) },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = Color.White
                )
            }
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                AppContent(koinInject())
                if (screenState.value.isProgressVisible) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )
}

@Composable
fun AppContent(screenState: MutableState<ScreenState>) {
    val currentScreen = remember { mutableStateOf(window.location.pathname) }
    println("webAppTAG Content currentScreen ${currentScreen.value}")
    DisposableEffect(Unit) {
        val onPopState: (Event) -> Unit = {
            println("webAppTAG Content onPopState ${it.type}")
            currentScreen.value = window.location.pathname
            println("webAppTAG Content onPopState ${it.type} currentScreen.value: ${currentScreen.value}")
        }
        window.addEventListener("popstate", onPopState)

        onDispose {
            window.removeEventListener("popstate", onPopState)
        }
    }
    println("webAppTAG Content  when  { currentScreen.value: ${currentScreen.value}")
    when  {
        currentScreen.value == "/list" || currentScreen.value.isBlank() || currentScreen.value == "/" -> {
            screenState.value = screenState.value.copy(
                floatingActionState = screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = true,
                    floatingActionButtonTitle = LocalStringResources.current.ADD,
                    floatingActionButtonAction = {
                        navigateTo("create/")
                    }
                )
            )
            ListScreen(screenState, onItemClick = { forkUI ->
                navigateTo("details/${forkUI.forkId}")
            }, content = { viewState, onItemClick ->
                ListContent(viewState.value, onItemClick)
            })
        }
        currentScreen.value.startsWith("/details/") -> {
            screenState.value = screenState.value.copy(
                floatingActionState = screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = true,
                    floatingActionButtonTitle = LocalStringResources.current.EDIT,
                    floatingActionButtonAction = {
                        navigateTo("edit/${currentScreen.value.removePrefix("/details/")}")
                    }
                )
            )
            DetailsScreen(currentScreen.value.removePrefix("/details/"), screenState) { viewState ->
                DetailsContent(viewState)
            }
        }
        currentScreen.value.startsWith("/edit/") -> {
            screenState.value = screenState.value.copy(
                floatingActionState = screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = false
                ))
            CreateScreen(currentScreen.value.removePrefix("/edit/"), screenState) { viewState, originFork, onClick ->
                CreateContent(viewState, originFork, onClick)
            }
        }
        else -> {
            screenState.value = screenState.value.copy(
                floatingActionState = screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = false
                ))
            CreateScreen("-1", screenState) { viewState, originFork, onClick ->
                CreateContent(viewState, originFork, onClick)
            }
        }
    }
}

fun navigateTo(path: String) {
    window.history.pushState(null, "", path)
    window.dispatchEvent(Event("popstate"))
}