package com.aspark.alarmclock

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {
    val driver: SqlDriver
}

//expect fun getDatabaseDriverFactory(): DatabaseDriverFactory