package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.textDecorationLine

// copied and adapted from `androidx.compose.ui.text.style.TextDecoration`
@Immutable
actual class TextDecoration internal constructor(val mask: Int) {
    actual companion object {
        actual val None = TextDecoration(0x0)
        actual val Underline = TextDecoration(0x1)
        actual val LineThrough = TextDecoration(0x2)

        actual fun combine(decorations: List<TextDecoration>): TextDecoration =
            TextDecoration(decorations.fold(0) { acc, decoration -> acc or decoration.mask })
    }

    actual operator fun contains(other: TextDecoration): Boolean =
        (mask or other.mask) == mask

    actual operator fun plus(decoration: TextDecoration): TextDecoration =
        TextDecoration(mask or decoration.mask)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TextDecoration) return false
        return mask == other.mask
    }

    override fun hashCode(): Int =
        mask

    override fun toString(): String =
        when {
            mask == 0 -> "TextDecoration.None"
            this == Underline -> "TextDecoration.Underline"
            this == LineThrough -> "TextDecoration.LineThrough"
            else -> "TextDecoration[Underline, LineThrough]"
        }
}

fun TextDecoration.toCssValue(): String =
    when {
        this == TextDecoration.None -> "none"
        this == TextDecoration.Underline -> "underline"
        this == TextDecoration.LineThrough -> "line-through"
        else -> buildList {
            if (TextDecoration.Underline in this@toCssValue) add("underline")
            if (TextDecoration.LineThrough in this@toCssValue) add("line-through")
        }.joinToString(" ")
    }

fun StyleScope.applyStyle(textDecoration: TextDecoration) {
    textDecorationLine(textDecoration.toCssValue())
}
