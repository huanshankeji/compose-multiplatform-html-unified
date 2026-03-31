package com.huanshankeji.compose.ui.text

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.TextUnit
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.graphics.toPlatformValue
import com.huanshankeji.compose.ui.text.font.FontStyle
import com.huanshankeji.compose.ui.text.font.FontWeight
import com.huanshankeji.compose.ui.text.style.TextDecoration
import androidx.compose.ui.graphics.Color as PlatformColor
import androidx.compose.ui.text.SpanStyle as PlatformSpanStyle

@Immutable
actual class SpanStyle actual constructor(
    actual val color: Color?,
    actual val fontSize: TextUnit,
    actual val fontWeight: FontWeight?,
    actual val fontStyle: FontStyle?,
    actual val letterSpacing: TextUnit,
    actual val textDecoration: TextDecoration?,
) {
    constructor(platformValue: PlatformSpanStyle) : this(
        color = if (platformValue.color == PlatformColor.Unspecified) null else Color(platformValue.color),
        fontSize = platformValue.fontSize,
        fontWeight = platformValue.fontWeight,
        fontStyle = platformValue.fontStyle,
        letterSpacing = platformValue.letterSpacing,
        textDecoration = platformValue.textDecoration,
    )

    fun toPlatformValue(): PlatformSpanStyle =
        PlatformSpanStyle(
            color = color.toPlatformValue(),
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
        )
}
