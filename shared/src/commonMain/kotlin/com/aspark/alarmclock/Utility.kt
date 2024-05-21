package com.aspark.alarmclock


class Utility {
    fun formatTime(hour24: Int, min: Int): String {

        var hour12 = hour24 % 12

        if (hour12 == 0) hour12 = 12

        return if (min / 10 == 0) "$hour12:0$min"
        else "$hour12:$min"

    }

    fun amOrPm(time: AlarmData): String {
        return if (time.hour < 12) "am" else "pm"
    }
}