package com.aspark.alarmclock.android.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.aspark.alarmclock.Receiver
import com.aspark.alarmclock.android.ui.AlarmActivity
import com.aspark.alarmclock.android.ui.MainActivity

class AlarmReceiver: BroadcastReceiver(), Receiver {
    override fun onReceive(context: Context?, intent: Intent?) {

        val id = intent?.getIntExtra("alarmId",-1)
        Log.i("AlarmReceiverAndroid", "onReceive: Alarm broadcast received for id: $id")

        val alarmIntent = Intent(context!!, AlarmActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        alarmIntent.putExtra("alarmId", id)
        context.startActivity(alarmIntent)
    }
}
