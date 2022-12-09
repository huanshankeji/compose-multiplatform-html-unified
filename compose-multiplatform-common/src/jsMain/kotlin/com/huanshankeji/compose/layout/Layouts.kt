package com.huanshankeji.compose.layout

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.ModifierOrAttrs
import com.huanshankeji.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLDivElement

actual typealias BoxElement = HTMLDivElement

//actual typealias BoxScope = ElementScope<HTMLDivElement>
actual interface BoxScope {
    val htmlDivElementScope: ElementScope<HTMLDivElement>

    class Impl(override val htmlDivElementScope: ElementScope<HTMLDivElement>) : BoxScope
}

@Composable
actual fun Box(modifierOrAttrs: ModifierOrAttrs<BoxElement>, content: @Composable BoxScope.() -> Unit) =
    Div(modifierOrAttrs.toAttrs()) { BoxScope.Impl(this).content() }
