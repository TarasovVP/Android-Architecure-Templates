package presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import presentation.create.CreateScreen
import presentation.details.DetailsScreen
import presentation.list.ListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {

        composable("list") {
            ListScreen({
                val forkId = it.toString()
                navController.navigate("details/$forkId")
            }, {
                navController.navigate("create")
            })
        }
        composable("details/{forkId}", arguments = listOf(navArgument("forkId") {
            type = NavType.StringType
            defaultValue = ""
        })) { backStackEntry ->
            val forkId = backStackEntry.arguments?.getString("forkId").orEmpty().toLong()
            DetailsScreen(forkId) {
                navController.popBackStack()
            }
        }
        composable("create") {
            CreateScreen {
                navController.popBackStack()
            }
        }
    }
}