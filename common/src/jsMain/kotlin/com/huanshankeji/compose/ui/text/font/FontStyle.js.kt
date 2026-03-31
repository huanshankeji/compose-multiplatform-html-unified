package com.huanshankeji.compose.ui.text.font

import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.fontStyle

// Copied and adapted from `FontStyle` in `androidx.compose.ui.text.font`. Do not edit without referencing to the original corresponding implementation.
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

        actual fun values(): List<FontStyle> = listOf(Normal, Italic)
    }

    fun toCssValue(): String =
        when (this) {
            Normal -> "normal"
            Italic -> "italic"
            else -> throw AssertionError()
        }
}

fun StyleScope.applyStyle(fontStyle: FontStyle) =
    fontStyle(fontStyle.toCssValue())
