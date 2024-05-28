package com.aspark.alarmclock

import com.aspark.alarm.db.AlarmSQLDelightDB

class AlarmRepository() {

    private val db: AlarmSQLDelightDB by lazy { SharedDatabase().initDatabase() }

    private val queries = db.alarmQueries

    fun insertAlarm(alarm: AlarmData) {
        val isSet = if (alarm.isOn) 1 else 0
        println("AlarmRepository - insert id:${alarm.id}")
        queries.insertAlarm( alarm.hour.toLong(), alarm.minute.toLong(), isSet.toLong())
    }

    fun getAllAlarm(): List<AlarmData> {
        return queries.getAllAlarm().executeAsList().map {
            val isSet = it.is_On.toInt() == 1
//            println("AlarmRepository - getAll id:${it.id}")
            AlarmData(it.id.toInt(), it.hour.toInt(), it.minute.toInt(), isSet)
        }
    }

    fun updateAlarmState(id: Int, isOn: Boolean) {

        println("AlarmRepository - update id:$id")
        val is_On = if (isOn) 1L else 0L
        queries.updateAlarmState(id = id.toLong(), isOn = is_On)
    }

    fun deleteAlarm(id: Int) {
        queries.deleteById(id.toLong())
    }
}