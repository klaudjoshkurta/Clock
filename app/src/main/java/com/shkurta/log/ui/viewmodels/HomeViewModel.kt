package com.shkurta.log.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shkurta.log.data.local.db.entity.LogEntity
import com.shkurta.log.data.repository.LogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logRepository: LogRepository
) : ViewModel() {

    val logs: StateFlow<List<LogEntity>> = logRepository.getAllLogs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun deleteLog(log: LogEntity) {
        viewModelScope.launch {
            logRepository.deleteLog(log)
        }
    }
}