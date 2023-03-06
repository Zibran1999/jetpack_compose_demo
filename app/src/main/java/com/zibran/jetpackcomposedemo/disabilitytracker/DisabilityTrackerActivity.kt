package com.zibran.jetpackcomposedemo.disabilitytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zibran.jetpackcomposedemo.R
import com.zibran.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class DisabilityTrackerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                ShowImage()

            }
        }
    }


    @Composable
    fun ShowImage() {
//        val scrollable = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
//                .verticalScroll(scrollable)
                .background(color = colorResource(id = R.color.off_white))
        ) {

            Image(
                painter = painterResource(id = R.drawable.hm_body),
                contentDescription = null,
                modifier = Modifier
                    .size(1000.dp)
                    .fillMaxWidth()

            )


        }

    }

    @Preview
    @Composable
    fun Preview() {
        JetpackComposeDemoTheme {
            ShowImage()
        }
    }
}
