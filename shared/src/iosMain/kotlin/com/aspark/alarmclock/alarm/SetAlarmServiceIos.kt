package com.aspark.alarmclock.alarm

import com.aspark.alarmclock.AlarmData
import com.aspark.alarmclock.Receiver

actual fun setAlarmService(time: AlarmData, alarmReceiver: Receiver): Unit {
    SetAlarmServiceIos().setAlarm()
}

class SetAlarmServiceIos {

    fun setAlarm() {


    }
}