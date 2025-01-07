package com.huanshankeji.androidx.lifecycle.viewmodel.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModelStoreOwner
import com.huanshankeji.compose.ui.platform.findComposeDefaultViewModelStoreOwner

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

@OptIn(InternalComposeApi::class)
@Composable
internal fun findViewTreeViewModelStoreOwner(): ViewModelStoreOwner? =
    findComposeDefaultViewModelStoreOwner()
