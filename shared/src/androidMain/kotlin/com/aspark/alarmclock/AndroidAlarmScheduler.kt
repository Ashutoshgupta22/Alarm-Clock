package com.aspark.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.Calendar

class AndroidAlarmScheduler(
    private val context: Context,
    private val alarmReceiver: Receiver
): AlarmScheduler {

    private val alarmManager: AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun scheduleAlarm(alarm: AlarmData) {

        val intent = Intent(context, alarmReceiver.javaClass)

        Log.i(this.javaClass.name, "setAlarm:id: ${alarm.id} ")
        intent.putExtra("alarmId", alarm.id)

        val requestCode = alarm.id
        val pendingIntent = PendingIntent.getBroadcast(context,
            requestCode, intent, PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, alarm.hour)
            set(Calendar.MINUTE, alarm.minute)
            set(Calendar.SECOND, 0)
        }

//        alarmManager.setAlarmClock(
//            AlarmManager.AlarmClockInfo(calendar.timeInMillis, pendingIntent), pendingIntent
//        )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent
        )
    }
}