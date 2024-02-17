package com.aspark.alarmclock.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aspark.alarmclock.MyTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _alarmList = MutableLiveData<MyTime>()
    val alarmList: LiveData<MyTime> = _alarmList

    fun getAllAlarmsFromDb() {

        viewModelScope.launch(Dispatchers.IO) {

        }

    }

}