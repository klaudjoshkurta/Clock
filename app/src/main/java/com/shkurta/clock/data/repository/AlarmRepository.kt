package com.shkurta.clock.data.repository

import com.shkurta.clock.alarm.AlarmScheduler
import com.shkurta.clock.data.Alarm
import com.shkurta.clock.database.AlarmDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AlarmRepository {
    fun getAlarms(): Flow<List<Alarm>>
    suspend fun addAlarm(alarm: Alarm)
}

class AlarmRepositoryImpl @Inject constructor(
    private val alarmDao: AlarmDao,
    private val alarmScheduler: AlarmScheduler
) : AlarmRepository {

    override fun getAlarms(): Flow<List<Alarm>> = alarmDao.getAlarms()

    override suspend fun addAlarm(alarm: Alarm) {
        alarmDao.insert(alarm)
        alarmScheduler.schedule(alarm)
    }
}