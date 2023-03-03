package com.zibran.jetpackcomposedemo.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CountViewModel : ViewModel() {

    var count by mutableStateOf(0)


    fun increaseCount() {
        count++
    }
}