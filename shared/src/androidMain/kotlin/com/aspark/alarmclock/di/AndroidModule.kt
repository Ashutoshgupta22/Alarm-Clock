package com.aspark.alarmclock.di

//import com.aspark.alarmclock.AndroidDbDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {

    single {
//        AndroidDb
    }

    single { androidContext() }
}