package com.shkurta.clock

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shkurta.clock.ui.screen.HomeScreen
import com.shkurta.clock.ui.screen.NewAlarmScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object NewAlarm : Screen("new_alarm")
}

@Composable
fun Clock() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.NewAlarm.route
        ) {
            NewAlarmScreen(navController = navController)
        }
    }
}