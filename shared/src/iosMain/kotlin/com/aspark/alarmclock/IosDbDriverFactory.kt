package com.aspark.alarmclock

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.aspark.alarm.db.AlarmSQLDelightDB

actual class DbDriverFactoryImpl: DatabaseDriverFactory {

     override val driver = NativeSqliteDriver(AlarmSQLDelightDB.Schema,
          "AlarmSQLDelightDB")
}

//actual fun getDatabaseDriverFactory(): DatabaseDriverFactory = IosDbDriverFactory()

