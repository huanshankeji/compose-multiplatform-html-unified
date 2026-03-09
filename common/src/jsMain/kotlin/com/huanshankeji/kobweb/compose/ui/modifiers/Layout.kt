package com.huanshankeji.kobweb.compose.ui.modifiers

import com.varabyte.kobweb.compose.css.Height
import com.varabyte.kobweb.compose.css.Width
import com.varabyte.kobweb.compose.css.height
import com.varabyte.kobweb.compose.css.width
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier


/**
 * A modifier for layouts to make them more consistent with the `androidx.compose` behavior.
 * `fit-content` is used for both `width` and `height` so the layout wraps its content,
 * like the default behavior of `Column`, `Row`, and `Box` in `androidx.compose`.
 *
 * Note: the max constraints (`max-width` and `max-height`) for imitating the `androidx.compose` behavior
 * of constraining content to parent bounds are not applied here,
 * because CSS `max-height: -webkit-fill-available` (and similar) does not properly chain
 * through nested flex containers the way `androidx.compose` constraints do,
 * and causes content to be hidden in non-scrollable contexts.
 * The max constraints are instead applied in the `*Scroll` modifiers where they are actually needed.
 *
 * @see com.huanshankeji.compose.foundation.ext.css.verticalScroll
 * @see com.huanshankeji.compose.foundation.ext.css.horizontalScroll
 */
fun Modifier.imitateComposeUiLayout() =
    styleModifier {
        width(Width.FitContent)
        height(Height.FitContent)
    }
