package com.huanshankeji.compose.ui.text

import com.huanshankeji.compose.ui.text.font.applyStyle
import com.huanshankeji.compose.ui.text.style.applyStyle
import com.huanshankeji.compose.ui.unit.applyFontSize
import com.huanshankeji.compose.ui.unit.applyLetterSpacing
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.color

fun StyleScope.applyStyle(spanStyle: SpanStyle) {
    spanStyle.color?.let { color(it.platformValue) }
    applyFontSize(spanStyle.fontSize)
    spanStyle.fontWeight?.let { applyStyle(it) }
    spanStyle.fontStyle?.let { applyStyle(it) }
    applyLetterSpacing(spanStyle.letterSpacing)
    spanStyle.textDecoration?.let { applyStyle(it) }
}
