package com.huanshankeji.kobweb.compose.ui.modifiers

import com.huanshankeji.compose.foundation.layout.ext.cssWidthStretchValueBrowserDependent
import com.varabyte.kobweb.compose.css.Height
import com.varabyte.kobweb.compose.css.Width
import com.varabyte.kobweb.compose.css.height
import com.varabyte.kobweb.compose.css.width
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier
import org.jetbrains.compose.web.css.maxWidth


/**
 * A modifier for layouts to make them more consistent with the `androidx.compose` behavior,
 * by adding the parent width as a max constraint for the children
 * (via `max-width: stretch` or browser-specific equivalents such as `-webkit-fill-available`).
 * Doing this prevents the children from pushing the parent out of the parent's parent horizontally,
 * and makes the `overflow-x` CSS property / the `*Scroll` modifier work better on JS DOM.
 *
 * Note: `max-height` is intentionally NOT applied here because it would cause content to be hidden
 * in Column layouts without vertical scroll. Instead, `max-height: stretch` is applied only when
 * vertical scroll is used (see [com.huanshankeji.compose.foundation.verticalScroll]).
 *
 * See https://developer.android.com/develop/ui/compose/layouts/constraints-modifiers for more details.
 */
fun Modifier.imitateComposeUiLayout() =
    styleModifier {
        width(Width.FitContent)
        height(Height.FitContent)
        maxWidth(cssWidthStretchValueBrowserDependent)
    }
