package presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.compose.koinInject
import presentation.details.DetailsScreen
import presentation.details.DetailsViewModel
import presentation.list.ListScreen
import presentation.list.ListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {

        composable("list") {
            val listViewModel = koinInject<ListViewModel>()
            ListScreen(listViewModel) {
            val forkId = it.toString()
            navController.navigate("details/$forkId")
        } }
        composable("details/{forkId}", arguments = listOf(navArgument("forkId") { type = NavType.StringType
            defaultValue = "" })) { backStackEntry ->
            val detailsViewModel = koinInject<DetailsViewModel>()
            val forkId = backStackEntry.arguments?.getString("forkId").orEmpty().toLong()
            DetailsScreen(detailsViewModel, forkId) {
                navController.popBackStack()
            }
        }
    }
}