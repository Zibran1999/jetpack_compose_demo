package com.zibran.jetpackcomposedemo.coroutineflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.zibran.jetpackcomposedemo.coroutineflow.ui.theme.JetpackComposeDemoTheme
import com.zibran.jetpackcomposedemo.viewModel.FlowViewModel

class CoroutineFlowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[FlowViewModel::class.java]
        setContent {
            val currentIndex = viewModel.myFlow.collectAsState(initial = 1).value
            ShowIndexValue(index = currentIndex)

        }
    }
}

@Composable
fun ShowIndexValue(index: Int) {
    Text(
        text = "Index is : $index",
        style = MaterialTheme.typography.h3,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxSize()
    )
}

@Preview
@Composable
fun PreviewIndex() {
    JetpackComposeDemoTheme() {
        ShowIndexValue(index = 9)
    }
}
