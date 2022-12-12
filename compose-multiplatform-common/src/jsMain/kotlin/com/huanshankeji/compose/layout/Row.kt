package com.huanshankeji.compose.layout

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.ModifierOrAttrs
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLDivElement

actual typealias RowElement = HTMLDivElement

actual interface RowScope {
    val elementScope: ElementScope<HTMLDivElement>

    class Impl(override val elementScope: ElementScope<HTMLDivElement>) : RowScope
}

@Composable
actual fun Row(modifierOrAttrs: ModifierOrAttrs<RowElement>, content: @Composable RowScope.() -> Unit) =
    // TODO: `modifierOrAttrs` not used yet
    com.huanshankeji.compose.web.Row {
        RowScope.Impl(this).content()
    }
