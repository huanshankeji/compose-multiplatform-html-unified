package com.huanshankeji.compose.ui.text.font

import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.fontStyle

fun FontStyle.toCssValue(): String =
    when (this) {
        FontStyle.Normal -> "normal"
        FontStyle.Italic -> "italic"
        else -> "normal"
    }

fun StyleScope.applyStyle(fontStyle: FontStyle) {
    fontStyle(fontStyle.toCssValue())
}
