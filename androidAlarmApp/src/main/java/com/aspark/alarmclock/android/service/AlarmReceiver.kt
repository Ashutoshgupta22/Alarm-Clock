package com.aspark.alarmclock.android.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.aspark.alarmclock.Receiver
import com.aspark.alarmclock.android.notification.MyNotification

class AlarmReceiver: BroadcastReceiver(), Receiver {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onReceive(context: Context?, intent: Intent?) {

        val id = intent?.getIntExtra("alarmId",-1)
        Log.i("AlarmReceiverAndroid", "onReceive: Alarm broadcast received for id: $id")

//        val alarmIntent = Intent(context!!, AlarmActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
//        }
//        alarmIntent.putExtra("alarmId", id)

//         MyNotification(context!!).apply {
//             createChannel()
//             showFullIntentNotification(id)
//         }

        val serviceIntent = Intent(context, AlarmService::class.java)
        serviceIntent.putExtra("alarmId", id)
        context?.startForegroundService(serviceIntent)

    }
}
