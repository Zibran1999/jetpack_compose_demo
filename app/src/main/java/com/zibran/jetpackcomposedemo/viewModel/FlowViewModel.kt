package com.zibran.jetpackcomposedemo.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowViewModel : ViewModel() {
    val myFlow = flow {
        for (i in 1..100) {
            emit(i)
            delay(1000L)
        }
    }
}