package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
expect class TextDecoration {
    val mask: Int

    companion object {
        @Stable val None: TextDecoration
        @Stable val Underline: TextDecoration
        @Stable val LineThrough: TextDecoration

        fun combine(decorations: List<TextDecoration>): TextDecoration
        fun valueOf(mask: Int): TextDecoration
    }

    operator fun plus(decoration: TextDecoration): TextDecoration
    operator fun contains(other: TextDecoration): Boolean
}
