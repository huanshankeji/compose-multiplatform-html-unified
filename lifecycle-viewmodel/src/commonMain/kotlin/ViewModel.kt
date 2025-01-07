package com.huanshankeji.androidx.lifecycle.viewmodel.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import kotlin.reflect.KClass

// https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-viewmodel.html

// copied and adapted from "ViewModel.kt" in `androidx.lifecycle.viewmodel.compose`


// `expect` can be removed if `expect object LocalViewModelStoreOwner` is added.
@PublishedApi
@Composable
internal expect fun defaultViewModelStoreOwner(): ViewModelStoreOwner

@PublishedApi
internal fun ViewModelStoreOwner.defaultCreationExtras(): CreationExtras =
    if (this is HasDefaultViewModelProviderFactory) {
        this.defaultViewModelCreationExtras
    } else {
        CreationExtras.Empty
    }


@Composable
expect fun <VM : ViewModel> viewModel(
    modelClass: KClass<VM>,
    viewModelStoreOwner: ViewModelStoreOwner = defaultViewModelStoreOwner(),
    key: String? = null,
    factory: ViewModelProvider.Factory? = null,
    extras: CreationExtras = viewModelStoreOwner.defaultCreationExtras()
): VM

@Composable
expect inline fun <reified VM : ViewModel> viewModel(
    viewModelStoreOwner: ViewModelStoreOwner = defaultViewModelStoreOwner(),
    key: String? = null,
    noinline initializer: CreationExtras.() -> VM
): VM

@Deprecated(
    "Use the one with a `viewModelStoreOwner` parameter instead. " +
            "This function might be removed in the future. " +
            "If you call this function with a `key` argument, make sure you used a named argument " +
            "so your source still compiles when this is removed."
)
@Composable
inline fun <reified VM : ViewModel> viewModel(
    key: String? = null,
    noinline initializer: CreationExtras.() -> VM
): VM =
    viewModel(defaultViewModelStoreOwner(), key, initializer)
