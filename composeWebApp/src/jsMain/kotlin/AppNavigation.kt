
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.vnteam.architecturetemplates.presentation.resources.LocalStringResources
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import kotlinx.browser.window
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
        currentScreen.value.startsWith("/details/") -> {
            screenState.value = screenState.value.copy(
                appBarState = screenState.value.appBarState.copy(
                    appBarTitle = LocalStringResources.current.CREATE
                ),
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
                appBarState = screenState.value.appBarState.copy(
                    appBarTitle = LocalStringResources.current.EDIT,
                    topAppBarAction = {
                        navigateUp()
                    }
                ),
                floatingActionState = screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = false
                ))
            CreateScreen(currentScreen.value.removePrefix("/edit/"), screenState) { viewState, originFork, onClick ->
                println("webAppTAG CommonTextField Content /edit/ originFork ${originFork.value}")
                CreateContent(viewState, originFork, onClick)
            }
        }
        currentScreen.value.startsWith("/create/") -> {
            screenState.value = screenState.value.copy(
                appBarState = screenState.value.appBarState.copy(
                    appBarTitle = LocalStringResources.current.CREATE,
                    topAppBarAction = {
                        navigateUp()
                    }
                ),
                floatingActionState = screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = false
                ))
            CreateScreen("", screenState) { viewState, originFork, onClick ->
                println("webAppTAG Content /create/ originFork ${originFork.value}")
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
    }
}

fun navigateTo(path: String) {
    window.history.pushState(null, "", path)
    window.dispatchEvent(Event("popstate"))
}

fun navigateUp() {
    window.history.back()
}