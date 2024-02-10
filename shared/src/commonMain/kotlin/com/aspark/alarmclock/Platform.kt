package com.aspark.alarmclock

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform