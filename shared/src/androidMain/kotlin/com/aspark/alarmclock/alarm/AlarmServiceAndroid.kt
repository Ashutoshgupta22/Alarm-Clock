package com.aspark.alarmclock.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.aspark.alarmclock.MyApplication
import com.aspark.alarmclock.MyTime
import com.aspark.alarmclock.Receiver
import java.util.Calendar

actual fun setAlarmService(time: MyTime, alarmReceiver: Receiver) {
    val context = MyApplication.getAppContext()
    AlarmServiceAndroid(context).setAlarm(time, alarmReceiver)
}

class AlarmServiceAndroid(private val context: Context) {

    private lateinit var alarmManager: AlarmManager

    fun setAlarm(time: MyTime, alarmReceiver: Receiver) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, alarmReceiver.javaClass)
        val pendingIntent = PendingIntent.getBroadcast(context,
            0, intent, PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, time.hour)
            set(Calendar.MINUTE, time.minute)
            set(Calendar.SECOND, 0)
        }

        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(calendar.timeInMillis, pendingIntent), pendingIntent
        )
    }
}