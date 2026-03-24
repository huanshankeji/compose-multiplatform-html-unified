package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.web.css.*

// copied and adapted from `androidx.compose.ui.text.style.TextOverflow`
@Immutable
actual value class TextOverflow internal constructor(internal val value: Int) {
    override fun toString(): String =
        when (this) {
            Clip -> "Clip"
            Ellipsis -> "Ellipsis"
            Visible -> "Visible"
            else -> "Invalid"
        }

    actual companion object {
        actual val Clip = TextOverflow(1)
        actual val Ellipsis = TextOverflow(2)
        actual val Visible = TextOverflow(3)
    }
}

fun StyleScope.applyStyle(textOverflow: TextOverflow, softWrap: Boolean, maxLines: Int) {
    if (!softWrap) {
        property("white-space", "nowrap")
    }
    when (textOverflow) {
        TextOverflow.Clip -> {
            property("overflow", "hidden")
            property("text-overflow", "clip")
        }

        TextOverflow.Ellipsis -> {
            property("overflow", "hidden")
            property("text-overflow", "ellipsis")
        }

        TextOverflow.Visible -> {
            // no overflow clipping
        }
    }
    if (maxLines != Int.MAX_VALUE) {
        property("display", "-webkit-box")
        property("-webkit-line-clamp", "$maxLines")
        property("-webkit-box-orient", "vertical")
        property("overflow", "hidden")
    }
}
