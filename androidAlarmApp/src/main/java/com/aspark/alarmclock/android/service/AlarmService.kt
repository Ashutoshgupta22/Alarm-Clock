package com.aspark.alarmclock.android.service

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ServiceCompat
import com.aspark.alarmclock.android.notification.MyNotification
import com.aspark.alarmclock.android.ui.AlarmActivity

class AlarmService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val alarmId = intent?.getIntExtra("alarmId", -1)

        val myNotification = MyNotification(applicationContext)
        myNotification.createChannel()
        val notification = myNotification.showFullIntentNotification(alarmId)

        Log.i("AlarmService", "onStartCommand: foreground service started")
        startForeground(alarmId!!, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)

        val intentAc = Intent(applicationContext, AlarmActivity::class.java)
        intentAc.flags  = Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(intentAc)

        return START_NOT_STICKY
    }
//
//    @RequiresApi(Build.VERSION_CODES.Q)
//    fun startForegroundService(notificationId: Int, notification: Notification) {
//
//        startForeground(
//            notificationId, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
//        )
//    }
}