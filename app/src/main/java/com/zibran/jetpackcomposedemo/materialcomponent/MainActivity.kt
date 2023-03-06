package com.zibran.jetpackcomposedemo.materialcomponent

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zibran.jetpackcomposedemo.dateandtimepicker.DateAndTimePickerActivity
import com.zibran.jetpackcomposedemo.disabilitytracker.DisabilityTrackerActivity
import com.zibran.jetpackcomposedemo.instagramprofile.InstaProfileUIActivity
import com.zibran.jetpackcomposedemo.navigation.NavigationActivity
import com.zibran.jetpackcomposedemo.notesapp.ui.NoteActivity
import com.zibran.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {

                LearningList(
                    listOf(
                        "Note App",
                        "Date And Time Picker",
                        "Disability Tracker",
                        "Instagram Profile UI",
                        "Navigation",
                        "Image with different shapes",
                        "Card",
                        "Check Box"

                    )
                )
            }
        }
    }

    @Composable
    private fun LearningList(listOf: List<String>) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()

        ) {

            items(items = listOf) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                        .clickable(true, onClick = {

                            onClick(note)

                        }), shape = RoundedCornerShape(
                        6.dp
                    )

                ) {
                    Column {
                        Text(
                            text = note,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 10.dp, top = 10.dp)

                        )
                        Spacer(modifier = Modifier.height(5.dp))

                    }
                }
            }

        }


    }

    private fun onClick(note: String) {
        when (note) {
            "Note App" -> {
                intent = Intent(this@MainActivity, NoteActivity::class.java)
                startActivity(intent)
            }
            "Date And Time Picker" -> {
                intent = Intent(this@MainActivity, DateAndTimePickerActivity::class.java)
                startActivity(intent)
            }
            "Disability Tracker" -> {
                intent = Intent(this@MainActivity, DisabilityTrackerActivity::class.java)
                startActivity(intent)
            }
            "Instagram Profile UI" -> {
                intent = Intent(this@MainActivity, InstaProfileUIActivity::class.java)
                startActivity(intent)
            }
            "Navigation" -> {
                intent = Intent(this@MainActivity, NavigationActivity::class.java)
                startActivity(intent)
            }
        }

    }
}
