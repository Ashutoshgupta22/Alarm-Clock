package com.aspark.alarmclock.alarm

import com.aspark.alarmclock.AlarmData
import com.aspark.alarmclock.AlarmScheduler
import platform.Foundation.NSCalendar
import platform.UserNotifications.UNCalendarNotificationTrigger
import platform.UserNotifications.UNMutableNotificationContent
import platform.UserNotifications.UNNotificationRequest
import platform.UserNotifications.UNNotificationSound.Companion.defaultSound
import platform.Foundation.NSDateComponents
import platform.Foundation.NSUUID.Companion.UUID
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptions
import platform.UserNotifications.UNUserNotificationCenter

class IOSAlarmScheduler : AlarmScheduler {

    override fun scheduleAlarm(alarm: AlarmData) {
        println("Scheduling IOS alarm notification")

        val content = UNMutableNotificationContent().apply {
            setTitle("Title")
            setBody("Sir, I found the body!")
            setSound(defaultSound)
        }

        val dateComponents = NSDateComponents().apply {
            setCalendar(NSCalendar.currentCalendar)
            setHour(alarm.hour.toLong())
            setMinute(alarm.minute.toLong())
        }

        val trigger = UNCalendarNotificationTrigger.triggerWithDateMatchingComponents(
            dateComponents, repeats = false
        )

        val request = UNNotificationRequest.requestWithIdentifier(
            identifier = UUID().UUIDString, content, trigger
        )

        val center = UNUserNotificationCenter.currentNotificationCenter()
        center.addNotificationRequest(request) { requestError ->
            if (requestError != null) {
                println(
                    "NotificationRequest add ERROR - ${requestError.localizedDescription}"
                )
            }
        }
    }

}