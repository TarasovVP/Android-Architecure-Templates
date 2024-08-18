package presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vnteam.architecturetemplates.presentation.resources.LocalStringResources
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import presentation.create.CreateScreen
import presentation.details.DetailsScreen
import presentation.list.ListScreen
import presentation.screens.create.CreateContent
import presentation.screens.details.DetailsContent
import presentation.screens.list.ListContent

@Composable
fun AppNavigation(navController: NavHostController, screenState: MutableState<ScreenState>) {

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            screenState.value = screenState.value.copy(
                topAppBarState = screenState.value.topAppBarState.copy(
                    topAppBarTitle = LocalStringResources.current.APP_NAME,
                    topAppBarActionVisible = false,
                ),
                floatingActionState = screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = true,
                    floatingActionButtonTitle = LocalStringResources.current.ADD,
                    floatingActionButtonAction = {
                        navController.navigate("create/-1")
                    }
                )
            )
            ListScreen(screenState, { forkUI ->
                navController.navigate("details/${forkUI.forkId}/${forkUI.name}")
            }, { viewState, onItemClick ->
                ListContent(viewState.value, onItemClick)
            })
        }
        composable("details/{forkId}/{forkName}", arguments = listOf(navArgument("forkId") {
            type = NavType.StringType
            defaultValue = ""
        }, navArgument("forkName") {
            type = NavType.StringType
            defaultValue = ""
        })) { backStackEntry ->
            val forkId = backStackEntry.arguments?.getString("forkId").orEmpty()
            val forkName = backStackEntry.arguments?.getString("forkName").orEmpty()
            screenState.value = screenState.value.copy(
                topAppBarState = screenState.value.topAppBarState.copy(
                    topAppBarTitle = forkName,
                    topAppBarActionVisible = true,
                    topAppBarAction = {
                        navController.popBackStack()
                    }
                ),
                floatingActionState = screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = true,
                    floatingActionButtonTitle = LocalStringResources.current.EDIT,
                    floatingActionButtonAction = {
                        navController.navigate("create/$forkId")
                    }
                )
            )
            DetailsScreen(forkId, screenState) { viewState ->
                DetailsContent(viewState)
            }
        }
        composable("create/{forkId}", arguments = listOf(navArgument("forkId") {
            type = NavType.StringType
            defaultValue = ""
            nullable = true
        })) { backStackEntry ->
            val forkId = backStackEntry.arguments?.getString("forkId").takeIf { it?.isNotEmpty() == true && it != "-1"} ?: ""
            screenState.value = screenState.value.copy(
                topAppBarState = screenState.value.topAppBarState.copy(
                    topAppBarTitle = if (forkId.isNotEmpty()) LocalStringResources.current.EDIT else LocalStringResources.current.CREATE,
                    topAppBarActionVisible = true,
                    topAppBarAction = {
                        navController.navigateUp()
                    }
                ),
                floatingActionState = screenState.value.floatingActionState.copy(
                floatingActionButtonVisible = false
            ))
            CreateScreen(forkId, screenState) { viewState, originFork, onClick ->
                CreateContent(viewState, originFork, onClick)
            }
        }
    }
}