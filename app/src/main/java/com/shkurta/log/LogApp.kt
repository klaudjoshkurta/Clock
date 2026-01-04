package com.shkurta.log

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shkurta.log.ui.screens.EntryScreen
import com.shkurta.log.ui.screens.HomeScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Entry : Screen("entry")
}

@Composable
fun LogApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                onNewLogClick = {
                    navController.navigate(Screen.Entry.route)
                }
            )
        }

        composable(
            route = Screen.Entry.route
        ) {
            EntryScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}