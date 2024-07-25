package presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vnteam.architecturetemplates.presentation.resources.LocalStringResources
import presentation.create.CreateScreen
import presentation.details.DetailsScreen
import presentation.list.ListScreen

@Composable
fun AppNavigation(screenState: MutableState<ScreenState>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            screenState.value = screenState.value.copy(
                topAppBarTitle = LocalStringResources.current.APP_NAME,
                topAppBarActionVisible = false,
                floatingActionButtonVisible = true,
                floatingActionButtonTitle = LocalStringResources.current.ADD,
                floatingActionButtonAction = {
                    navController.navigate("create/-1")
                }
            )
            ListScreen(screenState) { forkUI ->
                navController.navigate("details/${forkUI.forkId}/${forkUI.name}")
            }
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
                topAppBarTitle = forkName,
                topAppBarActionVisible = true,
                topAppBarAction = {
                    navController.popBackStack()
                },
                floatingActionButtonVisible = true,
                floatingActionButtonTitle = LocalStringResources.current.EDIT,
                floatingActionButtonAction = {
                    navController.navigate("create/$forkId")
                },
            )
            DetailsScreen(forkId, screenState)
        }
        composable("create/{forkId}", arguments = listOf(navArgument("forkId") {
            type = NavType.StringType
            defaultValue = ""
            nullable = true
        })) { backStackEntry ->
            val forkId = backStackEntry.arguments?.getString("forkId").takeIf { it?.isNotEmpty() == true && it != "-1"} ?: ""
            screenState.value = screenState.value.copy(
                topAppBarTitle = if (forkId.isNotEmpty()) LocalStringResources.current.EDIT else LocalStringResources.current.CREATE,
                topAppBarActionVisible = true,
                topAppBarAction = {
                    navController.navigateUp()
                },
                floatingActionButtonVisible = false
            )
            CreateScreen(forkId, screenState)
        }
    }
}