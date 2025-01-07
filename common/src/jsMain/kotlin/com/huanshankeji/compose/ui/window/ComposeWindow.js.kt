package com.huanshankeji.compose.ui.window

/*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.CompositionLocalProvider
import com.huanshankeji.compose.ui.platform.LocalInternalViewModelStoreOwner
import org.jetbrains.compose.web.dom.DOMScope
import org.jetbrains.compose.web.renderComposableInBody
import org.w3c.dom.HTMLBodyElement

// TODO not used yet so made private
private fun renderComposableInBodyWithLifecycle(
    content: @Composable DOMScope<HTMLBodyElement>.() -> Unit
): Composition =
    renderComposableInBody {
        // copied and adapted from `ComposeWindow` in "ComposeWindow.web.kt" in `androidx.compose.ui.window`
        // also see `ComposeViewport` on Wasm JS
        CompositionLocalProvider(
            //LocalSystemTheme provides systemThemeObserver.currentSystemTheme.value, // TODO add back if needed one day
            //LocalLifecycleOwner provides this, // TODO
            LocalInternalViewModelStoreOwner provides TODO(),
            content = {
                content()
            }
        )
    }
*/
