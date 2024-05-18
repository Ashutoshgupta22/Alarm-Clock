package com.aspark.alarmclock

import com.aspark.alarm.db.AlarmSQLDelightDB

class Repository() {

    private val db: AlarmSQLDelightDB by lazy { SharedDatabase().initDatabase() }

    private val queries = db.alarmQueries

    fun insert(alarmData: AlarmData) {
        val isSet = if (alarmData.isSet) 1 else 0
        queries.insert(null, alarmData.hour.toLong(), alarmData.minute.toLong(), isSet.toLong())
    }

    fun getAll(): List<AlarmData> {
        return queries.getAll().executeAsList().map {
            val isSet = it.is_set.toInt() == 1
            AlarmData(it.id.toInt(), it.hour.toInt(), it.minute.toInt(), isSet)
        }
    }

    fun updateAlarmSet(id: Int, isSet: Boolean) {

        println("DataSource - id:$id")
        val set = if (isSet) 1L else 0L
        queries.updateAlarmSet(id = id.toLong(), isSet = set)
    }

    fun deleteAlarm(id: Int) {
        queries.deleteById(id.toLong())
    }
}