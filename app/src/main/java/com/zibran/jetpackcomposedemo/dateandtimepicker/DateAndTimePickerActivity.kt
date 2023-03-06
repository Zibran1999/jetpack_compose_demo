package com.zibran.jetpackcomposedemo.dateandtimepicker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.zibran.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class DateAndTimePickerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {

                val calenderSheet = rememberSheetState()
                val clockSheet = rememberSheetState()
                CalendarDialog(
                    state = calenderSheet,
                    config = CalendarConfig(monthSelection = true, yearSelection = true),
                    selection = CalendarSelection.Date { date ->
                        Log.d("Date", "Selected Date: $date")
                    })

                ClockDialog(
                    state = clockSheet,
                    config = ClockConfig(
                        is24HourFormat = false
                    ),
                    selection = ClockSelection.HoursMinutes { hours, minutes ->
                        Log.d("Date", "Selected Date: $hours : $minutes")

                    })

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {

                        calenderSheet.show()
                    }) {
                        Text(text = "Date Picker")
                    }
                    Button(onClick = {
                        clockSheet.show()
                    }) {
                        Text(text = "Time Picker")
                    }

                }

            }
        }
    }
}

