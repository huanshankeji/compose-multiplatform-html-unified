package com.huanshankeji.compose.ui.platform

// copied and adapted from "DefaultViewModelOwnerStore.skiko.kt" in `androidx.compose.ui.platform`

import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModelStoreOwner

/**
 * Internal helper to provide [ViewModelStoreOwner] from Compose UI module.
 * In applications please use [androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner].
 *
 * @hide
 */
internal val LocalInternalViewModelStoreOwner = staticCompositionLocalOf<ViewModelStoreOwner?> {
    null
}

@InternalComposeApi
@Composable
fun findComposeDefaultViewModelStoreOwner(): ViewModelStoreOwner? =
    LocalInternalViewModelStoreOwner.current
