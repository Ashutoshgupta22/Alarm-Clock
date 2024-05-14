package com.aspark.alarmclock.android

import android.app.Application
import com.aspark.alarmclock.android.notification.MyNotification

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        MyNotification(applicationContext).createChannel()
    }
}