package com.huanshankeji.compose.ui.text.font

import androidx.compose.runtime.Immutable

// copied and adapted from `androidx.compose.ui.text.font.FontStyle`
@Immutable
@kotlin.jvm.JvmInline
value class FontStyle(val value: Int) {
    override fun toString(): String =
        when (this) {
            Normal -> "Normal"
            Italic -> "Italic"
            else -> "Invalid"
        }

    companion object {
        val Normal = FontStyle(0)
        val Italic = FontStyle(1)
    }
}
