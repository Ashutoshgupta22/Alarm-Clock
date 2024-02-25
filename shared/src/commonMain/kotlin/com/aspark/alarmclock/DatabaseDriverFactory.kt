package com.aspark.alarmclock

import app.cash.sqldelight.db.SqlDriver
import com.aspark.alarm.db.AlarmSQLDelightDB

interface DatabaseDriverFactory {
    val driver: SqlDriver
}

expect class DbDriverFactoryImpl(): DatabaseDriverFactory
//expect val databaseDriver: SqlDriver
//expect fun getDatabaseDriverFactory(): DatabaseDriverFactory

fun getDatabase(): AlarmSQLDelightDB = AlarmSQLDelightDB(DbDriverFactoryImpl().driver)