package com.aspark.alarmclock

import com.aspark.alarm.db.AlarmSQLDelightDB

class DataSource() {

    private val db: AlarmSQLDelightDB by lazy { getDatabase() }

    private val queries = db.alarmQueries

    fun insert(myTime: MyTime) {
        val isSet = if (myTime.isSet) 1 else 0
        queries.insert(null, myTime.hour.toLong(), myTime.minute.toLong(), isSet.toLong())
    }

    fun getAll(): List<MyTime> {
        return queries.getAll().executeAsList().map {
            val isSet = it.is_set.toInt() == 1
            MyTime(it.id.toInt(), it.hour.toInt(), it.minute.toInt(), isSet)
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