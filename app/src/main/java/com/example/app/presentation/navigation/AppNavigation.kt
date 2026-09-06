package com.example.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app.presentation.ProductScreen
import com.example.app.presentation.details.ProductDetailsScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(navController = navController,
        startDestination = "products") {
        composable("products"){
            ProductScreen(navController)
        }
        composable("products/{id}"){ backEntry ->
            val id = backEntry.arguments?.getString("id")
            ProductDetailsScreen(id)
        }
    }
}