package com.huanshankeji.compose.ui.text.style

import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.textAlign

fun TextAlign.toCssValue(): String =
    when (this) {
        TextAlign.Left -> "left"
        TextAlign.Right -> "right"
        TextAlign.Center -> "center"
        TextAlign.Justify -> "justify"
        TextAlign.Start -> "start"
        TextAlign.End -> "end"
        else -> "start"
    }

fun StyleScope.applyStyle(textAlign: TextAlign) {
    textAlign(textAlign.toCssValue())
}
