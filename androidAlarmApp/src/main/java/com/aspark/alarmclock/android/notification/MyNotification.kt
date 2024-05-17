package com.aspark.alarmclock.android.notification

import android.app.ActivityOptions
import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.aspark.alarmclock.android.R
import com.aspark.alarmclock.android.ui.AlarmActivity
import com.aspark.alarmclock.android.ui.MainActivity

const val CHANNEL_ID = "5780"

class MyNotification(private val context: Context) {

    private val notificationManager: NotificationManager = context.getSystemService(
        Application.NOTIFICATION_SERVICE ) as NotificationManager

    fun createChannel() {
        Log.i("NotificationAndroid", "onCreate: creating notification channel")

        val channel = NotificationChannel(
            CHANNEL_ID,
            "Alarm Notifications",
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationManager.createNotificationChannel(channel)
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    fun showFullIntentNotification(alarmId: Int?): Notification {

        val alarmIntent = Intent(context, AlarmActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        alarmIntent.putExtra("alarmId", alarmId)

        //For Android 14 and above to allow to start activity from background
//        val activityOptions = ActivityOptions.makeBasic().apply {
//
//            setPendingIntentBackgroundActivityStartMode(
//                ActivityOptions.MODE_BACKGROUND_ACTIVITY_START_ALLOWED
//            )
//        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            alarmId!!,
            alarmIntent,
            PendingIntent.FLAG_IMMUTABLE,
//            activityOptions.toBundle()
        )

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_alarm)
            .setContentTitle("Alarm")
            .setContentText("Content text")
            // prevents simultaneous appearing of notification in status bar with fullScreen intent
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setFullScreenIntent(pendingIntent, true)
            .build()

//        val serviceIntent = Intent(context, AlarmService::class.java)
//        context.startForegroundService(serviceIntent)
//        AlarmService().startForegroundService(101, notification)
//        notificationManager.notify(100, notification)
    }
}