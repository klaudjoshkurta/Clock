package com.shkurta.clock.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "alarm")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val time: Calendar,
    val label: String,
    val isEnabled: Boolean = true,
    val repeatDays: Set<Int> = emptySet()
)
