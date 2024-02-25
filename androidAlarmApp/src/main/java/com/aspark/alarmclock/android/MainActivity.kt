package com.aspark.alarmclock.android

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastAny
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.aspark.alarmclock.MyTime
import java.util.Calendar
import java.util.TreeSet

class MainActivity : ComponentActivity() {
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAllAlarmsFromDb()

        setContent {
            MyApplicationTheme {

                var showTimer by remember { mutableStateOf(false) }
                val alarmList = remember { mutableStateOf(listOf<MyTime>()) }

                viewModel.alarmList.observe(this) {
                    it?.let {
                        alarmList.value = it
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

//                    Box(modifier = Modifier.fillMaxSize())
//                    {
                        AlarmList(alarmList = alarmList.value)

                        Fab {
                            showTimer = true
                        }
                        if (showTimer) ShowTimer(
                            onConfirm = { hour, minute ->

                                val time = MyTime(hour, minute, true)
                                val isAlarmPresent = alarmList.value.any {
                                    it.hour == time.hour && it.minute == time.minute
                                }

                                if (! isAlarmPresent) {
                                    alarmList.value = alarmList.value.plus(time).sorted()
                                    viewModel.insert(time)
                                }
                                else Toast.makeText(this, "Alarm already added",
                                    Toast.LENGTH_SHORT).show()

                                showTimer = false

                            }, onDismiss = {
                                showTimer = false
                            }
                        )
//                    }
                }
            }
        }
    }
}

@Composable
private fun Fab(onFabClick: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {
        LargeFloatingActionButton(
            onClick = { onFabClick() },
            shape = CircleShape,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowTimer(onConfirm: (Int, Int) -> Unit, onDismiss: () -> Unit) {

    val calendar = Calendar.getInstance()
    val currentHour = calendar.get(Calendar.HOUR)
    val currentMinute = calendar.get(Calendar.MINUTE)

    val timeState = rememberTimePickerState(currentHour, currentMinute, false)

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(32.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Select time"
                    )
                }
                TimePicker(
                    state = timeState,
                    colors = TimePickerDefaults.colors(
                        Color.LightGray,
                        timeSelectorSelectedContainerColor = Color.LightGray
                    ),
                    layoutType = TimePickerLayoutType.Vertical
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.Absolute.Right
                ) {
                    TextButton(
                        onClick = { onDismiss() }
                    ) {
                        Text(text = "Dismiss")
                    }
                    TextButton(
                        onClick = {
                            onConfirm(timeState.hour, timeState.minute)
                        }
                    ) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
}

@Composable
private fun AlarmList(alarmList: List<MyTime>) {

    Log.i("MainActivity", "AlarmList: $alarmList")

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(alarmList) { index, time ->
            TimeCard(time = time)

            //conditional bottom padding
            val isLastItem = index == alarmList.lastIndex
            val bottomPadding = if (isLastItem) 130.dp else 0.dp
            Spacer(modifier = Modifier.height(bottomPadding))
        }
    }
}

@Composable
fun TimeCard(time: MyTime) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 8.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            val hour = time.hour % 12
            val amPm = if (time.hour < 12) "am" else "pm"
            val min = time.minute

            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 10.dp)
            ) {
                Text(text = "$hour:$min", fontSize = 40.sp)
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = amPm, fontSize = 18.sp)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                val isChecked = remember {
                    mutableStateOf(time.isSet)
                }
                Switch(
                    checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = !isChecked.value
                        time.isSet = isChecked.value
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            AlarmList(alarmList = mutableStateOf(listOf(MyTime(12, 34),
//                MyTime(2, 3)))
//            )
        }
    }
}
