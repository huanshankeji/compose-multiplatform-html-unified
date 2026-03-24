package com.huanshankeji.compose.ui.text.font

import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.fontWeight

fun FontWeight.toCssValue(): Int =
    weight

fun StyleScope.applyStyle(fontWeight: FontWeight) {
    fontWeight(fontWeight.toCssValue())
}
