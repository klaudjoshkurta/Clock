package com.shkurta.log.data.repository

import com.shkurta.log.data.local.db.dao.LogDao
import com.shkurta.log.data.local.db.entity.LogEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogRepository @Inject constructor(
    private val logDao: LogDao
) {
    fun getAllLogs(): Flow<List<LogEntity>> = logDao.getAllLogs()

    suspend fun insertLog(log: LogEntity) = logDao.insertLog(log)

    suspend fun updateLog(log: LogEntity) = logDao.updateLog(log)

    suspend fun deleteLog(log: LogEntity) = logDao.deleteLog(log)
}