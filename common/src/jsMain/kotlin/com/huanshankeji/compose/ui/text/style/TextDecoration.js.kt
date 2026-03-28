package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.textDecorationLine

// Copied and adapted from `TextDecoration` in `androidx.compose.ui.text.style`. Do not edit without referencing to the original corresponding implementation.
@Immutable
actual class TextDecoration internal constructor(actual val mask: Int) {

    actual companion object {
        @Stable actual val None: TextDecoration = TextDecoration(0x0)
        @Stable actual val Underline: TextDecoration = TextDecoration(0x1)
        @Stable actual val LineThrough: TextDecoration = TextDecoration(0x2)

        actual fun combine(decorations: List<TextDecoration>): TextDecoration {
            val mask = decorations.fold(0) { acc, decoration -> acc or decoration.mask }
            return TextDecoration(mask)
        }

        actual fun valueOf(mask: Int): TextDecoration {
            require((mask or 0b11) == 0b11) {
                "The given mask=$mask is not recognized by TextDecoration."
            }
            return when (mask) {
                0 -> None
                1 -> Underline
                2 -> LineThrough
                else -> TextDecoration(mask)
            }
        }
    }

    actual operator fun plus(decoration: TextDecoration): TextDecoration =
        TextDecoration(this.mask or decoration.mask)

    actual operator fun contains(other: TextDecoration): Boolean =
        (mask or other.mask) == mask

    override fun toString(): String {
        if (mask == 0) {
            return "TextDecoration.None"
        }
        val values: MutableList<String> = mutableListOf()
        if ((mask and Underline.mask) != 0) {
            values.add("Underline")
        }
        if ((mask and LineThrough.mask) != 0) {
            values.add("LineThrough")
        }
        if (values.size == 1) {
            return "TextDecoration.${values[0]}"
        }
        return "TextDecoration[${values.joinToString(separator = ", ")}]"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TextDecoration) return false
        if (mask != other.mask) return false
        return true
    }

    override fun hashCode(): Int =
        mask

    fun toCssValue(): String {
        if (this == None) return "none"
        return buildList {
            if ((mask and Underline.mask) != 0) add("underline")
            if ((mask and LineThrough.mask) != 0) add("line-through")
        }.joinToString(" ")
    }
}

fun StyleScope.applyStyle(textDecoration: TextDecoration) =
    textDecorationLine(textDecoration.toCssValue())
