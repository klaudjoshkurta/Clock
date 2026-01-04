package com.shkurta.log.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shkurta.log.data.local.db.entity.LogEntity
import com.shkurta.log.data.repository.LogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val logRepository: LogRepository
) : ViewModel() {

    fun saveLog(content: String) {
        val log = LogEntity(content = content)
        viewModelScope.launch {
            logRepository.insertLog(log)
        }
    }
}