package com.aspark.alarmclock.android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aspark.alarmclock.AlarmData
import com.aspark.alarmclock.Repository
import com.aspark.alarmclock.alarm.setAlarmService
import com.aspark.alarmclock.android.service.AlarmReceiver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val _alarmList = MutableLiveData<List<AlarmData>>()
    val alarmList: LiveData<List<AlarmData>> = _alarmList

    private val dataSource by lazy { Repository() }

    fun getAllAlarmsFromDb() {
        Log.i("MainViewModel", "getAllAlarmsFromDb: called")
        viewModelScope.launch(Dispatchers.IO) {
            _alarmList.postValue(dataSource.getAll())
        }
    }

    fun insert(time: AlarmData) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.insert(time)
            setAlarmService(time, AlarmReceiver())
        }
    }

    fun updateAlarmSet(time: AlarmData) {
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
            updateIsSetUi(id, false)
        }
    }

    private fun updateIsSetUi(id: Int, isSet: Boolean) {

        val alarm = _alarmList.value?.find {
            it.id == id
        }

        var index: Int? = null
        alarm?.let {
            it.isSet = isSet
            index = _alarmList.value?.indexOf(it)
        }

        index?.let {
            Log.i("MainViewModel", "updateIsSetUi: called")
            _alarmList.apply {
                value?.get(it)?.isSet  = isSet
                postValue(_alarmList.value)
            }
        }
    }
}