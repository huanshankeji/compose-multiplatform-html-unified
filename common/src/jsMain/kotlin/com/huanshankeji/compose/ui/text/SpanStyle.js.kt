package com.huanshankeji.compose.ui.text

import androidx.compose.ui.unit.TextUnit
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.text.font.FontStyle
import com.huanshankeji.compose.ui.text.font.FontWeight
import com.huanshankeji.compose.ui.text.font.applyStyle
import com.huanshankeji.compose.ui.text.style.TextDecoration
import com.huanshankeji.compose.ui.text.style.applyStyle
import com.huanshankeji.compose.ui.unit.applyFontSize
import com.huanshankeji.compose.ui.unit.applyLetterSpacing
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.color

actual class SpanStyle actual constructor(
    actual val color: Color?,
    actual val fontSize: TextUnit,
    actual val fontWeight: FontWeight?,
    actual val fontStyle: FontStyle?,
    actual val letterSpacing: TextUnit,
    actual val textDecoration: TextDecoration?,
)

fun StyleScope.applyStyle(spanStyle: SpanStyle) {
    spanStyle.color?.let { color(it.platformValue) }
    applyFontSize(spanStyle.fontSize)
    spanStyle.fontWeight?.let { applyStyle(it) }
    spanStyle.fontStyle?.let { applyStyle(it) }
    applyLetterSpacing(spanStyle.letterSpacing)
    spanStyle.textDecoration?.let { applyStyle(it) }
}
