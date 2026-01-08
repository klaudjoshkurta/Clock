package com.shkurta.clock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shkurta.clock.alarm.AlarmScheduler
import com.shkurta.clock.data.Alarm
import com.shkurta.clock.database.AlarmDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val alarmDao: AlarmDao,
    private val alarmScheduler: AlarmScheduler
) : ViewModel() {

    val alarms = alarmDao.getAlarms()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addAlarm(alarm: Alarm) {
        viewModelScope.launch {
            alarmDao.insert(alarm)
            alarmScheduler.schedule(alarm)
        }
    }
}