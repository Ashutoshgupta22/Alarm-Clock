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

    fun updateAlarmSet(myTime: MyTime) {
        val isSet = if (myTime.isSet) 1L else 0L
        myTime.id?.toLong()?.let { queries.updateAlarmSet(isSet, it) }
    }

    fun deleteAlarm(id: Int) {
        queries.deleteById(id.toLong())
    }
}