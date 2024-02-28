package com.aspark.alarmclock.alarm

import com.aspark.alarmclock.MyTime
import com.aspark.alarmclock.Receiver

actual fun setAlarmService(time: MyTime, alarmReceiver: Receiver): Unit {
    SetAlarmServiceIos().setAlarm()
}

class SetAlarmServiceIos {

    fun setAlarm() {


    }
}