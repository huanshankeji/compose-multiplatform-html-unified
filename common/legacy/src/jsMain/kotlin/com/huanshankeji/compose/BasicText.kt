package com.huanshankeji.compose

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.ModifierOrAttrs
import com.huanshankeji.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLSpanElement

actual typealias BasicTextElement = HTMLSpanElement

@Composable
actual fun BasicText(text: String, modifierOrAttrs: ModifierOrAttrs<HTMLSpanElement>) =
    Span(modifierOrAttrs.toAttrs()) { Text(text) }
