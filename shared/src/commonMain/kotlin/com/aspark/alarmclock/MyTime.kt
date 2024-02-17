package com.aspark.alarmclock

data class MyTime(
    val hour: Int,
    val minute: Int,
    var isSet: Boolean
) : Comparable<MyTime> {

    override fun compareTo(other: MyTime): Int {
        return if (hour == other.hour) minute - other.minute
        else hour - other.hour
    }
}
