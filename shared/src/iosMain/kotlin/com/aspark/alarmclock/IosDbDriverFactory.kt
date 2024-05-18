package com.aspark.alarmclock

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.aspark.alarm.db.AlarmSQLDelightDB

actual fun provideDbDriver(): SqlDriver {
     return NativeSqliteDriver(AlarmSQLDelightDB.Schema, "AlarmSQLDelightDB")
}

