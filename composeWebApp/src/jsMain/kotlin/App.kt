import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
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
import theme.AppTheme

@Composable
fun App(appViewModel: AppViewModel) {

    val isDarkTheme = appViewModel.isDarkTheme.collectAsState()
    val language = appViewModel.language.collectAsState()
    CompositionLocalProvider(LocalStringResources provides getStringResourcesByLocale(language.value.orEmpty())) {
        isDarkTheme.value?.let {
            AppTheme(it) {
                Content(koinInject())
            }
        } ?: SplashScreen()
    }
}

@Composable
fun Content(screenState: MutableState<ScreenState>) {
    val currentScreen = remember { mutableStateOf(window.location.pathname) }
    DisposableEffect(Unit) {
        val onPopState: (Event) -> Unit = {
            currentScreen.value = window.location.pathname
        }

        window.addEventListener("popstate", onPopState)

        onDispose {
            window.removeEventListener("popstate", onPopState)
        }
    }
    LaunchedEffect(Unit) {
        window.onpopstate = {
            window.location.pathname.let { currentScreen.value = it }
        }
    }

    when  {
        currentScreen.value == "/list" || currentScreen.value.isBlank() || currentScreen.value == "/" -> ListScreen(screenState) { navigateTo("/details/${it.forkId}") }
        currentScreen.value.startsWith("/details/") -> DetailsScreen(currentScreen.value.removePrefix("/details/"), screenState)
        else -> CreateScreen(currentScreen.value.removePrefix("/details/"), screenState)
    }
}

fun navigateTo(path: String) {
    window.history.pushState(null, "", path)
    window.dispatchEvent(Event("popstate"))
}