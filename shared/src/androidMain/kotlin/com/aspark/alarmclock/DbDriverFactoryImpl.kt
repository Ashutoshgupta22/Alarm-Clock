package com.aspark.alarmclock

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.aspark.alarm.db.AlarmSQLDelightDB


actual class DbDriverFactoryImpl: DatabaseDriverFactory {

    private val context by lazy { MyApplication.getAppContext()}

    override val driver = AndroidSqliteDriver(AlarmSQLDelightDB.Schema,
        context, "AlarmSQLDelightDB")
}

//actual fun getDatabaseDriverFactory(): DatabaseDriverFactory = DbDriverFactory()

//actual val databaseDriver: SqlDriver = AndroidSqliteDriver(AlarmSQLDelightDB.Schema,
////        context, "AlarmSQLDelightDB")