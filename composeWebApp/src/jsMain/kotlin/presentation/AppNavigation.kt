package presentation

import Constants.PATH_START
import Constants.POP_STATE
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.vnteam.architecturetemplates.presentation.NavigationScreens
import com.vnteam.architecturetemplates.presentation.resources.LocalStringResources
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import kotlinx.browser.window
import navigateTo
import navigateUp
import org.w3c.dom.events.Event
import presentation.create.CreateScreen
import presentation.details.DetailsScreen
import presentation.list.ListScreen
import presentation.screens.create.CreateContent
import presentation.screens.details.DetailsContent
import presentation.screens.list.ListContent

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
        currentScreen.value.startsWith("$PATH_START${NavigationScreens.DetailsScreen.route}") -> {
            screenState.value = screenState.value.copy(
                floatingActionState = screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = true,
                    floatingActionButtonTitle = LocalStringResources.current.EDIT,
                    floatingActionButtonAction = {
                        window.navigateTo("${NavigationScreens.EditScreen.route}${currentScreen.value.removePrefix("$PATH_START${NavigationScreens.DetailsScreen.route}")}")
                    }
                )
            )
            DetailsScreen(currentScreen.value.removePrefix("$PATH_START${NavigationScreens.DetailsScreen.route}"), screenState) { viewState ->
                screenState.value = screenState.value.copy(
                    appBarState = screenState.value.appBarState.copy(
                        appBarTitle = viewState.fork?.name.orEmpty()
                    )
                )
                DetailsContent(viewState)
            }
        }
        currentScreen.value.startsWith("$PATH_START${NavigationScreens.EditScreen.route}") -> {
            screenState.value = screenState.value.copy(
                appBarState = screenState.value.appBarState.copy(
                    appBarTitle = LocalStringResources.current.EDIT,
                    topAppBarAction = {
                        window.navigateUp()
                    }
                ),
                floatingActionState = screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = false
                ))
            CreateScreen(currentScreen.value.removePrefix("$PATH_START${NavigationScreens.EditScreen.route}"), screenState) { viewState, originFork, onClick ->
                CreateContent(viewState, originFork, onClick)
            }
        }
        currentScreen.value.startsWith("$PATH_START${NavigationScreens.CreateScreen.route}") -> {
            screenState.value = screenState.value.copy(
                appBarState = screenState.value.appBarState.copy(
                    appBarTitle = LocalStringResources.current.CREATE,
                    topAppBarAction = {
                        window.navigateUp()
                    }
                ),
                floatingActionState = screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = false
                ))
            CreateScreen("", screenState) { viewState, originFork, onClick ->
                CreateContent(viewState, originFork, onClick)
            }
        }
        else -> {
            screenState.value = screenState.value.copy(
                appBarState = screenState.value.appBarState.copy(
                    appBarTitle = LocalStringResources.current.APP_NAME
                ),
                floatingActionState = screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = true,
                    floatingActionButtonTitle = LocalStringResources.current.ADD,
                    floatingActionButtonAction = {
                        window.navigateTo(NavigationScreens.CreateScreen.route)
                    }
                )
            )
            ListScreen(screenState, onItemClick = { forkUI ->
                window.navigateTo("${NavigationScreens.DetailsScreen.route}${forkUI.forkId}")
            }, content = { viewState, onItemClick ->
                ListContent(viewState.value, onItemClick)
            })
        }
    }
}