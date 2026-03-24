package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Immutable

// copied and adapted from `androidx.compose.ui.text.style.TextAlign`
@Immutable
@kotlin.jvm.JvmInline
value class TextAlign internal constructor(internal val value: Int) {
    override fun toString(): String =
        when (this) {
            Left -> "Left"
            Right -> "Right"
            Center -> "Center"
            Justify -> "Justify"
            Start -> "Start"
            End -> "End"
            else -> "Invalid"
        }

    companion object {
        val Left = TextAlign(1)
        val Right = TextAlign(2)
        val Center = TextAlign(3)
        val Justify = TextAlign(4)
        val Start = TextAlign(5)
        val End = TextAlign(6)
    }
}
