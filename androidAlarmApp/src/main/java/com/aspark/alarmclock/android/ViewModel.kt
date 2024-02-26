package com.aspark.alarmclock.android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aspark.alarmclock.DataSource
import com.aspark.alarmclock.MyTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel() {

    private val _alarmList = MutableLiveData<List<MyTime>>()
    val alarmList: LiveData<List<MyTime>> = _alarmList

    private val dataSource by lazy{ DataSource()}

    fun getAllAlarmsFromDb() {
        Log.i("MainViewModel", "getAllAlarmsFromDb: called")
        viewModelScope.launch(Dispatchers.IO) {
            _alarmList.postValue(dataSource.getAll())
        }
    }

    fun insert(time: MyTime) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.insert(time)
        }
    }

    fun updateAlarmSet(time: MyTime) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.updateAlarmSet(time)
        }
    }

    fun deleteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.deleteAlarm(id)
        }
    }
}