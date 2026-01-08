package com.shkurta.clock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shkurta.clock.data.Alarm
import com.shkurta.clock.ui.navigation.NavGraph
import com.shkurta.clock.ui.theme.ClockTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClockTheme {
                NavGraph()
            }
        }
    }
}

@Composable
fun AlarmList(alarms: List<Alarm>, modifier: Modifier = Modifier, onAddAlarm: () -> Unit) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = onAddAlarm) {
                Icon(Icons.Default.Add, contentDescription = "Add Alarm")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(alarms) { alarm ->
                AlarmItem(alarm = alarm)
            }
        }
    }
}

@Composable
fun AlarmItem(alarm: Alarm, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            val hour = alarm.time.get(Calendar.HOUR_OF_DAY)
            val minute = alarm.time.get(Calendar.MINUTE)
            Text(
                text = String.format("%02d:%02d", hour, minute),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = alarm.label,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Switch(
            checked = alarm.isEnabled,
            onCheckedChange = { /* TODO */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlarmListPreview() {
    ClockTheme {
        val sampleAlarms = listOf(
            Alarm(id = 1, time = Calendar.getInstance().apply { set(Calendar.HOUR_OF_DAY, 6); set(Calendar.MINUTE, 30) }, label = "Morning Alarm", isEnabled = true),
            Alarm(id = 2, time = Calendar.getInstance().apply { set(Calendar.HOUR_OF_DAY, 22); set(Calendar.MINUTE, 0) }, label = "Bedtime", isEnabled = false)
        )
        AlarmList(alarms = sampleAlarms, onAddAlarm = {})
    }
}
