package com.shkurta.clock.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.shkurta.clock.data.Alarm
import com.shkurta.clock.ui.components.DayOfWeekSelector
import com.shkurta.clock.ui.viewmodel.AlarmViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
) {
    val viewModel: AlarmViewModel = hiltViewModel()
    val alarms by viewModel.alarms.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Alarms") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("new_alarm") }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Alarm",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            items(alarms) { alarm ->
                AlarmItem(
                    alarm = alarm,
                    onToggleAlarm = { viewModel.toggleAlarm(it) },
                    onRepeatDaysChange = { updatedAlarm, repeatDays -> viewModel.updateRepeatDays(updatedAlarm, repeatDays) }
                )
                if (alarms.indexOf(alarm) < alarms.size - 1) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.12f))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmItem(
    modifier: Modifier = Modifier,
    alarm: Alarm,
    onToggleAlarm: (Alarm) -> Unit = {},
    onRepeatDaysChange: (Alarm, Set<Int>) -> Unit = { _, _ -> }
) {
    val sheetState = rememberModalBottomSheetState()
    var showAlarmDetails by remember { mutableStateOf(false) }

    val hour = alarm.time.get(Calendar.HOUR_OF_DAY)
    val minute = alarm.time.get(Calendar.MINUTE)

    Box(
        modifier = modifier
            .clickable { showAlarmDetails = true }
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (alarm.label.isNotEmpty()) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = alarm.label,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = String.format("%02d:%02d", hour, minute),
                    style = MaterialTheme.typography.headlineMedium
                )
                Switch(
                    checked = alarm.isEnabled,
                    onCheckedChange = { onToggleAlarm(alarm) }
                )
            }
        }
    }

    if (showAlarmDetails) {
        ModalBottomSheet(
            onDismissRequest = { showAlarmDetails = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = String.format("%02d:%02d", hour, minute),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Alarm name",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                DayOfWeekSelector(
                    selectedDays = alarm.repeatDays,
                    onDaySelected = {
                        val newRepeatDays = if (alarm.repeatDays.contains(it)) {
                            alarm.repeatDays - it
                        } else {
                            alarm.repeatDays + it
                        }
                        onRepeatDaysChange(alarm, newRepeatDays)
                    }
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Sound",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "Default",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Vibrate",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Switch(
                                checked = alarm.isEnabled,
                                onCheckedChange = { onToggleAlarm(alarm) }
                            )
                        }
                    }
                }
                TextButton(
                    onClick = { showAlarmDetails = false }
                ) {
                    Text("Delete")
                }
            }
        }
    }
}