package com.aspark.alarmclock

import android.app.Application
import android.content.Context

class MyApplicationAndroid: Application() {

    companion object {
        private lateinit var instance: Application

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()

//        initKoin()
        instance = this
    }
}
