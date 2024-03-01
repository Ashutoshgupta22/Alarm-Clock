package com.aspark.alarmclock.android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aspark.alarmclock.DataSource
import com.aspark.alarmclock.MyTime
import com.aspark.alarmclock.alarm.setAlarmService
import com.aspark.alarmclock.android.service.AlarmReceiver
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(): ViewModel() {

    private val _alarmList = MutableLiveData<List<MyTime>>()
    val alarmList: LiveData<List<MyTime>> = _alarmList

    private val dataSource by lazy{ DataSource()}
//    private val alarmService by la

    fun getAllAlarmsFromDb() {
        Log.i("MainViewModel", "getAllAlarmsFromDb: called")
        viewModelScope.launch(Dispatchers.IO) {
            _alarmList.postValue(dataSource.getAll())
        }
    }

    fun insert(time: MyTime) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.insert(time)
            setAlarmService(time, AlarmReceiver())
        }
    }

    fun updateAlarmSet(time: MyTime) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.updateAlarmSet(time.id, time.isSet)
        }
    }

    fun deleteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.deleteAlarm(id)
        }
    }

    fun alarmSetFalse(id: Int) {
         viewModelScope.launch(Dispatchers.IO) {
            Log.i("MainViewModel", "alarmSetFalse: called")
            dataSource.updateAlarmSet(id, false)
        }

        val def = viewModelScope.async(Dispatchers.IO) {
            Log.i("MainViewModel", "alarmSetFalse: async called")
        }
        suspend{ def.await()
            Log.i("MainViewModel", "alarmSetFalse: await called")}

        Log.i("MainViewModel", "outer called ")
    }
}