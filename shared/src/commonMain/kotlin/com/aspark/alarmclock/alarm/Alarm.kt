package com.aspark.alarmclock.alarm

import com.aspark.alarmclock.MyTime
import com.aspark.alarmclock.Receiver

expect fun setAlarmService(time: MyTime, alarmReceiver: Receiver)