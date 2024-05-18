package com.aspark.alarmclock

import app.cash.sqldelight.db.SqlDriver
import com.aspark.alarm.db.AlarmSQLDelightDB

expect fun provideDbDriver(): SqlDriver

class SharedDatabase {
    private var database: AlarmSQLDelightDB? = null

     fun initDatabase(): AlarmSQLDelightDB {

        if (database == null)
            database = AlarmSQLDelightDB.invoke(provideDbDriver())

         return database!!
    }
}