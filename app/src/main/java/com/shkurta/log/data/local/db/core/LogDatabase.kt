package com.shkurta.log.data.local.db.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shkurta.log.data.local.db.dao.LogDao
import com.shkurta.log.data.local.db.entity.LogEntity

@Database(entities = [LogEntity::class], version = 1, exportSchema = false)
abstract class LogDatabase : RoomDatabase() {
    abstract fun logDao(): LogDao
}