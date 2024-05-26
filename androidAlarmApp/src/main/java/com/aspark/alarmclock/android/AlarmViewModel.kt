package com.aspark.alarmclock.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aspark.alarmclock.AlarmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmViewModel: ViewModel() {

    private val repo by lazy { AlarmRepository() }

    fun alarmSetFalse(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("AlarmViewModel", "alarmSetFalse: called")
            repo.updateAlarmState(id, false)
        }
    }
}