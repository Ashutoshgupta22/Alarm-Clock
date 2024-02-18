package com.aspark.alarmclock

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.aspark.alarm.db.AlarmSQLDelightDB

class AndroidDbDriverFactory(context: Context): DatabaseDriverFactory {

    override val driver = AndroidSqliteDriver(AlarmSQLDelightDB.Schema,
        context, "AlarmSQLDelightDB")
}

//actual fun getDatabaseDriverFactory(): DatabaseDriverFactory = AndroidDbDriverFactory()