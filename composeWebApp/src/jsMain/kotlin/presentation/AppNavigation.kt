package presentation

import Constants.PATH_START
import Constants.POP_STATE
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.vnteam.architecturetemplates.presentation.NavigationScreens
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import kotlinx.browser.window
import navigateTo
import org.w3c.dom.events.Event
import presentation.create.CreateScreen
import presentation.details.DetailsScreen
import presentation.list.ListScreen
import presentation.screens.create.CreateContent
import presentation.screens.details.DetailsContent
import presentation.screens.list.ListContent
import presentation.screens.page_not_found.PageNotFound

@Composable
fun AppNavigation(screenState: MutableState<ScreenState>) {
    val currentScreen = remember { mutableStateOf(window.location.pathname) }
    DisposableEffect(Unit) {
        val onPopState: (Event) -> Unit = {
            currentScreen.value = window.location.pathname
        }
        window.addEventListener(POP_STATE, onPopState)

        onDispose {
            window.removeEventListener(POP_STATE, onPopState)
        }
    }
    when  {
        currentScreen.value == "" || currentScreen.value == PATH_START -> {
            ListScreen(screenState, onItemClick = { forkUI ->
                window.navigateTo("${NavigationScreens.DetailsScreen.route}${forkUI.forkId}")
            }, content = { viewState, onItemClick ->
                ListContent(viewState.value, screenState, onItemClick)
            })
        }
        currentScreen.value.startsWith("$PATH_START${NavigationScreens.DetailsScreen.route}") -> {
            DetailsScreen(currentScreen.value.removePrefix("$PATH_START${NavigationScreens.DetailsScreen.route}"), screenState) { viewState ->
                DetailsContent(viewState, screenState)
            }
        }
        currentScreen.value.startsWith("$PATH_START${NavigationScreens.EditScreen.route}") -> {
            CreateScreen(currentScreen.value.removePrefix("$PATH_START${NavigationScreens.EditScreen.route}"), screenState) { viewState, originFork, onClick ->
                CreateContent(viewState, screenState, originFork, onClick)
            }
        }
        currentScreen.value.startsWith("$PATH_START${NavigationScreens.CreateScreen.route}") -> {
            CreateScreen("", screenState) { viewState, originFork, onClick ->
                CreateContent(viewState, screenState, originFork, onClick)
            }
        }
        else -> {
            PageNotFound()
        }
    }
}