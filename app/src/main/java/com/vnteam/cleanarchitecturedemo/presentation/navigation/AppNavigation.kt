package com.vnteam.cleanarchitecturedemo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vnteam.cleanarchitecturedemo.presentation.details.DetailsScreen
import com.vnteam.cleanarchitecturedemo.presentation.list.ListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") { ListScreen() {
            navController.navigate("details/$it")
        } }
        composable("details/{forkId}") { backStackEntry ->
            val forkId = backStackEntry.arguments?.getString("forkId").orEmpty().toLong()
            DetailsScreen(forkId) {
                navController.popBackStack()
            }
        }
    }
}