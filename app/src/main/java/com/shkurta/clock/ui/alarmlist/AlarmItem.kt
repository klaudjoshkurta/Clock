package com.shkurta.clock.ui.alarmlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shkurta.clock.data.Alarm
import java.util.Calendar

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