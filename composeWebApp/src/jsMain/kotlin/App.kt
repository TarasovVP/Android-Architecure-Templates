import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vnteam.architecturetemplates.presentation.resources.LocalStringResources
import com.vnteam.architecturetemplates.presentation.resources.getStringResourcesByLocale
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import com.vnteam.architecturetemplates.presentation.viewmodels.AppViewModel
import kotlinx.browser.window
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
                Content()
            }
        } ?: SplashScreen()
    }
}

@Composable
fun Content() {
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
    Column {
        Text(text = "webAppTAG Content currentScreen.value: ${currentScreen.value}", Modifier.align(Alignment.Start).padding(16.dp).background(Color.Gray))
        println("webAppTAG Content  when  { currentScreen.value: ${currentScreen.value}")
        when  {
            currentScreen.value == "/list" || currentScreen.value.isBlank() || currentScreen.value == "/" -> ListScreen(onItemClick = { forkUI ->
                navigateTo("/details/$forkUI")
            }, content = { viewState, onItemClick ->
                ListContent(viewState.value, onItemClick)
            })
            currentScreen.value.startsWith("/details/") -> DetailsScreen(currentScreen.value.removePrefix("/details/")) { viewState ->
                DetailsContent(viewState)
            }
            else -> CreateScreen(currentScreen.value.removePrefix("/details/")) { viewState, originFork, onClick ->
                CreateContent(viewState, originFork, onClick)
            }
        }
    }
}

fun navigateTo(path: String) {
    window.history.pushState(null, "", path)
    window.dispatchEvent(Event("popstate"))
}