package com.aspark.alarmclock.di

import com.aspark.alarmclock.DbDriverFactoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {

    single {
        DbDriverFactoryImpl()
    }

    single { androidContext() }
}