package com.example.a29_rpmy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier
){
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = SealedScreens.Main.route,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        composable(SealedScreens.Main.route){
            MainScreen(navController,sharedViewModel)
        }

        composable(SealedScreens.Order.route){
            MakeOrder(navController)
        }

        // Добавьте эти маршруты
        composable(SealedScreens.PizzaMenu.route){
            PizzaMenu(navController, sharedViewModel) // Убедитесь, что у вас есть этот composable
        }

        composable(SealedScreens.PastaMenu.route){
            PastaMenu(navController) // Убедитесь, что у вас есть этот composable
        }

        composable(SealedScreens.EstablishmentMenu.route){
            EstablishmentMenu(navController)
        }

        composable (
            route = SealedScreens.PizzaDetails.route,
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ){
                backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            PizzaDetails(navController,sharedViewModel)
        }

        composable(
            route = SealedScreens.PastaDetails.route,
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ){
                backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            PastaDetails(navController, name,description)
        }

        composable(
            route = SealedScreens.EstablishmentDetails.route,
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("space") { type = NavType.StringType },
                navArgument("timeWork") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val space = backStackEntry.arguments?.getString("space") ?: ""
            val timeWork = backStackEntry.arguments?.getString("timeWork") ?: ""
            EstablishmentDetails(navController, name, space, timeWork)
        }
    }
}