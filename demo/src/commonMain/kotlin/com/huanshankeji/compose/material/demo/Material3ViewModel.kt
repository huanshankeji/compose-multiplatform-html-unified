package com.huanshankeji.compose.material.demo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class Material3ViewModel : ViewModel() {
    val countState = MutableStateFlow(0)
    val checkedState = MutableStateFlow(false)
}