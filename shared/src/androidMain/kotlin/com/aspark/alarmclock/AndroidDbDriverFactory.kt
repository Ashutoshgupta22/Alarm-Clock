package com.aspark.alarmclock

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.aspark.alarm.db.AlarmSQLDelightDB

private val context by lazy { MyApplicationAndroid.getAppContext()}

actual fun provideDbDriver(): SqlDriver {
    return AndroidSqliteDriver(AlarmSQLDelightDB.Schema, context, "AlarmSQLDelightDb")
}

//actual class AndroidDbDriverFactory: DatabaseDriverFactory {
//
//    private val context by lazy { MyApplicationAndroid.getAppContext()}
//
//    override val driver = AndroidSqliteDriver(AlarmSQLDelightDB.Schema,
//        context, "AlarmSQLDelightDB")
//}

//actual fun getDatabaseDriverFactory(): DatabaseDriverFactory = DbDriverFactory()

//actual val databaseDriver: SqlDriver = AndroidSqliteDriver(AlarmSQLDelightDB.Schema,
////        context, "AlarmSQLDelightDB")