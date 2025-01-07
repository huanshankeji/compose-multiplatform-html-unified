package com.huanshankeji.androidx.lifecycle.viewmodel.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlin.reflect.KClass

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
): VM = viewModelStoreOwner.get(modelClass, key, factory, extras)

@Composable
actual inline fun <reified VM : ViewModel> viewModel(
    viewModelStoreOwner: ViewModelStoreOwner,
    key: String?,
    noinline initializer: CreationExtras.() -> VM
): VM = viewModel(
    VM::class,
    viewModelStoreOwner,
    key,
    viewModelFactory { initializer(initializer) },
    viewModelStoreOwner.defaultCreationExtras()
)

// TODO remove
/*
@Composable
actual inline fun <reified VM : ViewModel> viewModel(
    viewModelStoreOwner: ViewModelStoreOwner, key: String?, noinline initializer: CreationExtras.() -> VM
): VM =
    remember(key) { CreationExtras.Empty.initializer() }
*/

internal fun <VM : ViewModel> ViewModelStoreOwner.get(
    modelClass: KClass<VM>,
    key: String?,
    factory: ViewModelProvider.Factory?,
    extras: CreationExtras
): VM {
    val provider = if (factory != null) {
        ViewModelProvider.create(this.viewModelStore, factory, extras)
    } else if (this is HasDefaultViewModelProviderFactory) {
        ViewModelProvider.create(this.viewModelStore, this.defaultViewModelProviderFactory, extras)
    } else {
        ViewModelProvider.create(this)
    }
    return if (key != null) {
        provider[key, modelClass]
    } else {
        provider[modelClass]
    }
}
