package com.shkurta.clock.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun DayOfWeekSelector(
    selectedDays: Set<Int>,
    onDaySelected: (Int) -> Unit
) {
    val days = listOf(
        "S" to Calendar.SUNDAY,
        "M" to Calendar.MONDAY,
        "T" to Calendar.TUESDAY,
        "W" to Calendar.WEDNESDAY,
        "T" to Calendar.THURSDAY,
        "F" to Calendar.FRIDAY,
        "S" to Calendar.SATURDAY
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        days.forEach { (day, calendarDay) ->
            val isSelected = selectedDays.contains(calendarDay)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(CircleShape)
                    .background(
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                    )
                    .clickable { onDaySelected(calendarDay) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}