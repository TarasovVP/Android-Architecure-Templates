package presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import presentation.details.DetailsScreen
import presentation.list.ListScreen

@Composable
fun AppNavigation(screenState: MutableState<ScreenState>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            screenState.value = ScreenState().apply {
                topAppBarTitle = getStringResources().FORKS
            }
            ListScreen(screenState) { forkUI ->
                navController.navigate("details/${forkUI.id}/${forkUI.name}")
            }
        }
        composable("details/{forkId}/{forkName}", arguments = listOf(navArgument("forkId") {
            type = NavType.StringType
            defaultValue = ""
        }, navArgument("forkName") {
            type = NavType.StringType
            defaultValue = ""
        })) { backStackEntry ->
            val forkId = backStackEntry.arguments?.getString("forkId").orEmpty().toLong()
            val forkName = backStackEntry.arguments?.getString("forkName").orEmpty()
            screenState.value = ScreenState().apply {
                topAppBarTitle = forkName
                topAppBarActionVisible = true
                topAppBarAction = {
                    navController.popBackStack()
                }
            }
            DetailsScreen(forkId, screenState)
        }
    }
}