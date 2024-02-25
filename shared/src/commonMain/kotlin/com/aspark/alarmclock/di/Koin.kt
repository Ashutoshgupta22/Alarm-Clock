package com.aspark.alarmclock.di

import com.aspark.alarm.db.AlarmSQLDelightDB
import com.aspark.alarmclock.DataSource
import com.aspark.alarmclock.DatabaseDriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    single {
//        DataSource( get() )
    }

    single {
        AlarmSQLDelightDB(get())
    }

//    single { DbDriverFactory() }
}