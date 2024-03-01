package com.aspark.alarmclock.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aspark.alarmclock.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmViewModel: ViewModel() {

    private val dataSource by lazy { DataSource() }

    fun alarmSetFalse(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("AlarmViewModel", "alarmSetFalse: called")
            dataSource.updateAlarmSet(id, false)
        }
    }
}