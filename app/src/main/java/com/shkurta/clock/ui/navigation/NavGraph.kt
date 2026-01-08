package com.shkurta.clock.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shkurta.clock.AlarmList
import com.shkurta.clock.data.Alarm
import com.shkurta.clock.ui.addalarm.AddAlarmScreen
import com.shkurta.clock.viewmodel.AlarmViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: AlarmViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "alarmList") {
        composable("alarmList") {
            val alarms by viewModel.alarms.collectAsState()
            AlarmList(alarms = alarms, onAddAlarm = {
                navController.navigate("addAlarm")
            })
        }
        composable("addAlarm") {
            AddAlarmScreen(
                onSave = { time, label ->
                    viewModel.addAlarm(Alarm(time = time, label = label))
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}