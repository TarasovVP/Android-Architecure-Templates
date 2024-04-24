package com.vnteam.architecturetemplates

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vnteam.architecturetemplates.presentation.details.DetailsScreen
import com.vnteam.architecturetemplates.presentation.details.DetailsViewModel
import com.vnteam.architecturetemplates.presentation.list.ListScreen
import com.vnteam.architecturetemplates.presentation.list.ListViewModel
import org.koin.compose.koinInject

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {

        //TODO change forkId implementation

        composable("list") {
            val listViewModel = koinInject<ListViewModel>()
            ListScreen(listViewModel) { forkId ->
                navController.currentBackStackEntry?.arguments?.putLong("forkId", forkId)
                navController.previousBackStackEntry?.arguments?.putLong("forkId", forkId)
                navController.currentDestination?.arguments?.plus("forkId" to forkId)
                navController.navigate("details")
            }
        }
        composable("details") { backStackEntry ->
            val testNavController = navController
            val forkId = backStackEntry.arguments?.getLong("forkId")
            val detailsViewModel = koinInject<DetailsViewModel>()
            val current = testNavController.currentBackStackEntry?.arguments?.getLong("forkId", forkId!!)
            val previous = testNavController.previousBackStackEntry?.arguments?.getLong("forkId", forkId!!)
            val currentDestination = testNavController.currentDestination?.arguments?.get("forkId")
            DetailsScreen(detailsViewModel, forkId) {
                navController.popBackStack()
            }
        }
    }
}