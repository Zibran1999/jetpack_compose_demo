package com.zibran.jetpackcomposedemo.timer

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.zibran.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class TimerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {

            }
        }
    }
}

@Composable
fun Timer(totalTime: Long, handleColor: Color, inactiveColor: Color, modifier: Modifier = Modifier, initalValue: Float = 0f, strokeWith: Dp = 5.dp,
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initalValue)
    }

    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var isTimerRunnig by remember {
        mutableStateOf(false)
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.onSizeChanged { size = it }) {

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    JetpackComposeDemoTheme {
//        Greeting2("Android")
    }
}