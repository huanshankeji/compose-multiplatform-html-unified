package com.huanshankeji.compose.ui.text.style

import androidx.compose.runtime.Stable
import com.varabyte.kobweb.compose.css.*
import org.jetbrains.compose.web.css.StyleScope
import com.varabyte.kobweb.compose.css.TextOverflow as CssTextOverflow

// Copied and adapted from `TextOverflow` in `androidx.compose.ui.text.style`. Do not edit without referencing to the original corresponding implementation.
actual value class TextOverflow internal constructor(internal val value: Int) {
    override fun toString(): String =
        when (this) {
            Clip -> "Clip"
            Ellipsis -> "Ellipsis"
            Visible -> "Visible"
            else -> "Invalid"
        }

    actual companion object {
        @Stable actual val Clip = TextOverflow(1)
        @Stable actual val Ellipsis = TextOverflow(2)
        @Stable actual val Visible = TextOverflow(3)
    }
}

/**
 * Applies text overflow, wrapping, and line clamping CSS styles.
 *
 * Note on `maxLines` vs `minLines` CSS implementation inconsistency:
 * - `maxLines` uses `-webkit-line-clamp` with `display: -webkit-box` and `-webkit-box-orient: vertical`,
 *   which is the only widely-supported CSS mechanism for multi-line text truncation.
 *   The standard `line-clamp` property (without `-webkit-` prefix) is not yet widely supported.
 * - `minLines` uses `min-height` with `lh` (line-height) units, as there is no CSS equivalent to `min-line-clamp`.
 *
 * Note on `TextOverflow.Visible` degradation when `maxLines` is set:
 * `-webkit-line-clamp` requires `overflow: hidden` to function, so `TextOverflow.Visible`
 * effectively degrades to `Clip` when `maxLines` is set on JS DOM. There is no CSS way
 * to both limit visible lines and allow text to overflow beyond its bounds.
 *
 * Note on `softWrap` and `maxLines` interaction:
 * In Compose UI, when `softWrap` is `false`, the text is always single-line and `maxLines` is ignored.
 * On JS DOM, `white-space: nowrap` achieves the same single-line behavior, so `-webkit-line-clamp`
 * is only applied when `softWrap` is `true`.
 */
fun StyleScope.applyStyle(textOverflow: TextOverflow, softWrap: Boolean, maxLines: Int) {
    if (!softWrap) {
        whiteSpace(WhiteSpace.NoWrap)
    }

    when (textOverflow) {
        TextOverflow.Clip -> {
            overflow(Overflow.Hidden)
            textOverflow(CssTextOverflow.Clip)
        }

        TextOverflow.Ellipsis -> {
            overflow(Overflow.Hidden)
            textOverflow(CssTextOverflow.Ellipsis)
        }

        TextOverflow.Visible -> {
            // no overflow clipping
        }
    }

    // `-webkit-line-clamp` requires `display: -webkit-box`, `-webkit-box-orient: vertical`,
    // and `overflow: hidden` to function. Only apply when softWrap is true, since
    // `white-space: nowrap` already constrains to a single line when softWrap is false.
    if (softWrap && maxLines != Int.MAX_VALUE) {
        property("display", "-webkit-box")
        property("-webkit-line-clamp", "$maxLines")
        property("-webkit-box-orient", "vertical")
        overflow(Overflow.Hidden)
    }
}
