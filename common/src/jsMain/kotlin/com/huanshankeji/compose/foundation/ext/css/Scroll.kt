package com.huanshankeji.compose.foundation.ext.css

import com.huanshankeji.compose.foundation.layout.ext.cssHeightStretchValueBrowserDependent
import com.huanshankeji.compose.foundation.layout.ext.cssWidthStretchValueBrowserDependent
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.overflowX
import com.varabyte.kobweb.compose.css.overflowY
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.maxHeight
import org.jetbrains.compose.web.css.maxWidth

/**
 * The max constraint is added here (rather than in `imitateComposeUiLayout`)
 * so that scroll containers have a definite max size to scroll within,
 * while non-scrollable layouts don't get their content hidden by the max constraint.
 *
 * @see com.huanshankeji.kobweb.compose.ui.modifiers.imitateComposeUiLayout
 */
fun StyleScope.horizontalScroll() {
    maxWidth(cssWidthStretchValueBrowserDependent)
    overflowX(Overflow.Auto)
}

/**
 * @see horizontalScroll
 */
fun StyleScope.verticalScroll() {
    maxHeight(cssHeightStretchValueBrowserDependent)
    //overflowY(Overflow.Scroll)
    overflowY(Overflow.Auto)
}
