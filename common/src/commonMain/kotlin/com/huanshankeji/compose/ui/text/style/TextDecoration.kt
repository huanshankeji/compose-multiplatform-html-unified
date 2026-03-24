package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Immutable

// copied and adapted from `androidx.compose.ui.text.style.TextDecoration`
@Immutable
class TextDecoration internal constructor(val mask: Int) {
    companion object {
        val None = TextDecoration(0x0)
        val Underline = TextDecoration(0x1)
        val LineThrough = TextDecoration(0x2)
    }

    operator fun contains(other: TextDecoration): Boolean =
        (mask or other.mask) == mask

    operator fun plus(decoration: TextDecoration): TextDecoration =
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

fun TextDecoration.Companion.combine(decorations: List<TextDecoration>): TextDecoration =
    TextDecoration(decorations.fold(0) { acc, decoration -> acc or decoration.mask })
