package com.aspark.alarmclock.di

import com.aspark.alarmclock.DbDriverFactoryImpl
import org.koin.dsl.module

val iosModule = module {
    single { DbDriverFactoryImpl() }
}