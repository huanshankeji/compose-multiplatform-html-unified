package com.huanshankeji.compose.ui.window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.ui.platform.LocalInternalViewModelStoreOwner
import org.jetbrains.compose.web.dom.DOMScope
import org.jetbrains.compose.web.renderComposableInBody
import org.w3c.dom.HTMLBodyElement

@ExperimentalApi
class SimpleViewModelStoreOwner : ViewModelStoreOwner {
    override val viewModelStore: ViewModelStore = ViewModelStore()
}

fun renderComposableInBodyWithViewModelStoreOwner(
    content: @Composable DOMScope<HTMLBodyElement>.() -> Unit
): Composition =
    renderComposableInBody {
        // copied and adapted from `ComposeWindow` in "ComposeWindow.web.kt" in `androidx.compose.ui.window`
        // also see `ComposeViewport` on Wasm JS
        @OptIn(ExperimentalApi::class)
        CompositionLocalProvider(
            /* TODO add back these 2 lines below if needed one day
                in a function possibly named `renderComposableInBodyWithLifecycle` */
            //LocalSystemTheme provides systemThemeObserver.currentSystemTheme.value,
            //LocalLifecycleOwner provides this,
            LocalInternalViewModelStoreOwner provides SimpleViewModelStoreOwner(),
            content = {
                content()
            }
        )
    }
