package com.aspark.alarmclock

data class AlarmData(
    val id: Int,
    val hour: Int,
    val minute: Int,
    var isSet: Boolean
) : Comparable<AlarmData> {

    override fun compareTo(other: AlarmData): Int {
        return if (hour == other.hour) minute - other.minute
        else hour - other.hour
    }
}
