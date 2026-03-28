package com.huanshankeji.compose.ui.text.font

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.fontStyle

// copied and adapted from `androidx.compose.ui.text.font.FontStyle`
@Immutable
actual value class FontStyle private actual constructor(actual val value: Int) {
    override fun toString(): String =
        when (this) {
            Normal -> "Normal"
            Italic -> "Italic"
            else -> "Invalid"
        }

    actual companion object {
        actual val Normal = FontStyle(0)
        actual val Italic = FontStyle(1)
    }
}

fun FontStyle.toCssValue(): String =
    when (this) {
        FontStyle.Normal -> "normal"
        FontStyle.Italic -> "italic"
        else -> "normal"
    }

fun StyleScope.applyStyle(fontStyle: FontStyle) {
    fontStyle(fontStyle.toCssValue())
}
