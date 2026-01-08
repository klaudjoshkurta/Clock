package com.shkurta.clock.database

import androidx.room.TypeConverter
import java.util.Calendar

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Calendar? {
        return value?.let { Calendar.getInstance().apply { timeInMillis = it } }
    }

    @TypeConverter
    fun toTimestamp(calendar: Calendar?): Long? {
        return calendar?.timeInMillis
    }

    @TypeConverter
    fun fromIntSet(value: String?): Set<Int> {
        if (value.isNullOrBlank()) {
            return emptySet()
        }
        return value.split(',').mapNotNull { it.toIntOrNull() }.toSet()
    }

    @TypeConverter
    fun toIntSet(set: Set<Int>?): String? {
        return set?.joinToString(",")
    }
}