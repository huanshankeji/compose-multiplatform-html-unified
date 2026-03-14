package com.huanshankeji.kobweb.compose.ui.modifiers

import com.varabyte.kobweb.compose.css.Height
import com.varabyte.kobweb.compose.css.Width
import com.varabyte.kobweb.compose.css.height
import com.varabyte.kobweb.compose.css.width
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier


/**
 * A modifier for layouts to make them more consistent with the Compose UI behavior.
 * `fit-content` is used for both `width` and `height` so the layout wraps its content tightly,
 * like the default behavior of `Column`, `Row`, and `Box` in Compose UI.
 *
 * Note: the max constraints (`max-width` and `max-height`) for imitating the Compose UI behavior
 * of constraining content to parent bounds used to be applied here but are not now,
 * because `max-height: -webkit-fill-available` (and similar) causes overflowing content to be clipped
 * in layouts without `overflow: auto/scroll`, where the height is capped but there is no scroll
 * to reach the hidden content.
 * The max constraints are instead applied in the `*Scroll` modifiers where they are actually needed.
 *
 * @see com.huanshankeji.compose.foundation.ext.css.horizontalScroll
 * @see com.huanshankeji.compose.foundation.ext.css.verticalScroll
 */
fun Modifier.imitateComposeUiLayout() =
    styleModifier {
        width(Width.FitContent)
        height(Height.FitContent)
    }
