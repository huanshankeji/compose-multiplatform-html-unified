package com.huanshankeji.androidx.lifecycle.viewmodel.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModelStoreOwner

// copied and adapted from "LocalViewModelStoreOwner.kt" and "LocalViewModelStoreOwner.jb.kt" in `androidx.lifecycle.viewmodel.compose`

object LocalViewModelStoreOwner {
    private val LocalViewModelStoreOwner =
        compositionLocalOf<ViewModelStoreOwner?> { null }
    val current: ViewModelStoreOwner?
        @Composable
        get() = LocalViewModelStoreOwner.current ?: findViewTreeViewModelStoreOwner()

    infix fun provides(viewModelStoreOwner: ViewModelStoreOwner):
            ProvidedValue<ViewModelStoreOwner?> {
        return LocalViewModelStoreOwner.provides(viewModelStoreOwner)
    }
}

@Composable
internal fun findViewTreeViewModelStoreOwner(): ViewModelStoreOwner? =
    // TODO
    null //findComposeDefaultViewModelStoreOwner()
