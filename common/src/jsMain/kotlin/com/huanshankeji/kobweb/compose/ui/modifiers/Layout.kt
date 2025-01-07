package com.huanshankeji.kobweb.compose.ui.modifiers

import com.huanshankeji.compose.foundation.layout.ext.cssHeightStretchValueBrowserDependent
import com.huanshankeji.compose.foundation.layout.ext.cssWidthStretchValueBrowserDependent
import com.varabyte.kobweb.compose.css.Height
import com.varabyte.kobweb.compose.css.Width
import com.varabyte.kobweb.compose.css.height
import com.varabyte.kobweb.compose.css.width
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier
import org.jetbrains.compose.web.css.maxHeight
import org.jetbrains.compose.web.css.maxWidth


/**
 * A modifier for layouts to make them more consistent with the `androidx.compose` behavior,
 * by adding the parent sizes as max constrains for the children.
 * Doing this prevents the children to push the parent out of the parent's parent,
 * and make the `overflow-*` CSS property / the `*Scroll` modifier work better on JS DOM.
 * See https://developer.android.com/develop/ui/compose/layouts/constraints-modifiers for more details.
 */
fun Modifier.imitateComposeUiLayout() =
    styleModifier {
        width(Width.FitContent)
        height(Height.FitContent)
        maxWidth(cssWidthStretchValueBrowserDependent)
        maxHeight(cssHeightStretchValueBrowserDependent)
    }
