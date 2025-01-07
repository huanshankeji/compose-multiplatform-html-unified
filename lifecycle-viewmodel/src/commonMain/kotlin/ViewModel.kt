package com.huanshankeji.androidx.lifecycle.viewmodel.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras

// https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-viewmodel.html

@Composable
expect inline fun <reified VM : ViewModel> viewModel(
    key: String? = null,
    noinline initializer: CreationExtras.() -> VM
): VM
