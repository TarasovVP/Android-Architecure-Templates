package presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import presentation.create.CreateScreen
import presentation.details.DetailsScreen
import presentation.list.ListScreen

@Composable
fun AppNavigation(scaffoldState: MutableState<ScaffoldState>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            scaffoldState.value = ScaffoldState().apply {
                topAppBarTitle = getStringResources().APP_NAME
                floatingActionButtonVisible = true
                floatingActionButtonTitle = getStringResources().ADD
                floatingActionButtonAction = {
                    navController.navigate("create")
                }
            }
            ListScreen {
                val forkId = it.toString()
                navController.navigate("details/$forkId")
            }
        }
        composable("details/{forkId}", arguments = listOf(navArgument("forkId") {
            type = NavType.StringType
            defaultValue = ""
        })) { backStackEntry ->
            scaffoldState.value = ScaffoldState().apply {
                topAppBarTitle = "details"
                topAppBarActionVisible = true
                topAppBarAction = {
                    navController.navigateUp()
                }
            }
            val forkId = backStackEntry.arguments?.getString("forkId").orEmpty().toLong()
            DetailsScreen(forkId) {
                navController.popBackStack()
            }
        }
        composable("create") {
            scaffoldState.value = ScaffoldState().apply {
                topAppBarTitle = "create"
                topAppBarActionVisible = true
                topAppBarAction = {
                    navController.navigateUp()
                }
            }
            CreateScreen()
        }
    }
}