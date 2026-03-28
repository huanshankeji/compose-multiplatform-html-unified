package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.textAlign

// copied and adapted from `androidx.compose.ui.text.style.TextAlign`
@Immutable
actual value class TextAlign internal constructor(internal val value: Int) {
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
    }
}

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
