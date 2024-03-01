package com.aspark.alarmclock.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
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

        Log.i("AlarmServiceAndroid", "setAlarm:id: ${time.id} ")
        intent.putExtra("alarmId", time.id)

        val requestCode = time.id
        val pendingIntent = PendingIntent.getBroadcast(context,
            requestCode, intent, PendingIntent.FLAG_MUTABLE)

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