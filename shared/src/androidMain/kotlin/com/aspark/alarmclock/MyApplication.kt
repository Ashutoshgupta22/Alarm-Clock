package com.aspark.alarmclock

import android.app.Application
import android.content.Context
import android.util.Log

class MyApplication: Application() {

    companion object {
        private lateinit var instance: Application

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()

        Log.i("MyApplication Android", "onCreate: Init Koin")
//        initKoin()
        instance = this
    }
}
