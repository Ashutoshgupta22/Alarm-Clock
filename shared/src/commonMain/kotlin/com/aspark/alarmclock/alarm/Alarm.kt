package com.aspark.alarmclock.alarm

import com.aspark.alarmclock.AlarmData
import com.aspark.alarmclock.Receiver

expect fun setAlarmService(time: AlarmData, alarmReceiver: Receiver)