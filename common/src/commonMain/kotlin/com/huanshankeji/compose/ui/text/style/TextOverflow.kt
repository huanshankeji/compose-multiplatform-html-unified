package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Immutable

// copied and adapted from `androidx.compose.ui.text.style.TextOverflow`
@Immutable
@kotlin.jvm.JvmInline
value class TextOverflow internal constructor(internal val value: Int) {
    override fun toString(): String =
        when (this) {
            Clip -> "Clip"
            Ellipsis -> "Ellipsis"
            Visible -> "Visible"
            else -> "Invalid"
        }

    companion object {
        val Clip = TextOverflow(1)
        val Ellipsis = TextOverflow(2)
        val Visible = TextOverflow(3)
    }
}
