package com.huanshankeji.compose.ui.text.style

import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.textAlign

// Copied and adapted from `TextAlign` in `androidx.compose.ui.text.style`. Do not edit without referencing to the original corresponding implementation.
actual value class TextAlign internal constructor(actual val value: Int) {
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

    actual companion object {
        actual val Left = TextAlign(1)
        actual val Right = TextAlign(2)
        actual val Center = TextAlign(3)
        actual val Justify = TextAlign(4)
        actual val Start = TextAlign(5)
        actual val End = TextAlign(6)

        actual fun values(): List<TextAlign> = listOf(Left, Right, Center, Justify, Start, End)
    }

    fun toCssValue(): String =
        when (this) {
            Left -> "left"
            Right -> "right"
            Center -> "center"
            Justify -> "justify"
            Start -> "start"
            End -> "end"
            else -> throw AssertionError()
        }
}

fun StyleScope.applyStyle(textAlign: TextAlign) =
    textAlign(textAlign.toCssValue())
