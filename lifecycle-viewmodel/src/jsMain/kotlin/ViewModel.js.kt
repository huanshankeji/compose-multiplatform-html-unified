package com.huanshankeji.androidx.lifecycle.viewmodel.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras

@Composable
actual inline fun <reified VM : ViewModel> viewModel(key: String?, noinline initializer: CreationExtras.() -> VM): VM =
    remember(key) { CreationExtras.Empty.initializer() }
