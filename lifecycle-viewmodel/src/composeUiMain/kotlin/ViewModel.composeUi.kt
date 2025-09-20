package com.huanshankeji.androidx.lifecycle.viewmodel.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import kotlin.reflect.KClass
import androidx.lifecycle.viewmodel.compose.viewModel as composeUiViewModel

// copied and adapted from "ViewModel.kt" in `androidx.lifecycle.viewmodel.compose`
@PublishedApi
@Composable
internal actual fun defaultViewModelStoreOwner(): ViewModelStoreOwner =
    checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }

@Composable
actual fun <VM : ViewModel> viewModel(
    modelClass: KClass<VM>,
    viewModelStoreOwner: ViewModelStoreOwner,
    key: String?,
    factory: ViewModelProvider.Factory?,
    extras: CreationExtras
): VM =
    composeUiViewModel(modelClass, viewModelStoreOwner, key, factory, extras)

@Composable
actual inline fun <reified VM : ViewModel> viewModel(
    viewModelStoreOwner: ViewModelStoreOwner,
    key: String?,
    noinline initializer: CreationExtras.() -> VM
): VM =
    composeUiViewModel(viewModelStoreOwner, key, initializer)
