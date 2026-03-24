package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Immutable

@Immutable
expect class TextDecoration {
    companion object {
        val None: TextDecoration
        val Underline: TextDecoration
        val LineThrough: TextDecoration

        fun combine(decorations: List<TextDecoration>): TextDecoration
    }

    operator fun contains(other: TextDecoration): Boolean
    operator fun plus(decoration: TextDecoration): TextDecoration
}
