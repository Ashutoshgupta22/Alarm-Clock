package com.aspark.alarmclock.android.ui

import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.aspark.alarmclock.android.AlarmViewModel
import com.aspark.alarmclock.android.MainViewModel
import com.aspark.alarmclock.android.ui.ui.theme.Alarm_ClockTheme

class AlarmActivity : ComponentActivity() {

    private lateinit var ringtone: Ringtone
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
//            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
//            keyguardManager.requestDismissKeyguard(this, null)
        }
        else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
        }

        val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this, ringtoneUri)
        ringtone.play()

        val alarmId = intent.getIntExtra("alarmId", -1)
        viewModel.alarmSetFalse(alarmId)

        setContent {
            Alarm_ClockTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting {
                        ringtone.stop()
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(onStop: () -> Unit) {

    Box(Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
        ) {

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Good morning, darling",
                fontSize = 50.sp,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 0.dp)
            )

            Spacer(modifier = Modifier.height(55.dp))

            Button(
                modifier = Modifier.wrapContentSize(),
                onClick = {
                    onStop()
                }
            ) {
                Text(text = "Stop Alarm")
            }
        }
    }
}

//@Composable
//fun HideStatusBar() {
//
//    SideEffect {
//        val windowInsetsController = WindowInsetsC
//        windowInsetsController?.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//        windowInsetsController?.hide(WindowInsetsCompat.Type.statusBars())
//    }
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Alarm_ClockTheme {
//        Greeting(ringtone)
    }
}