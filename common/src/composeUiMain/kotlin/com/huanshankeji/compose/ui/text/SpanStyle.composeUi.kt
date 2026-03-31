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
actual class SpanStyle(val platformValue: PlatformSpanStyle) {
    actual constructor(
        color: Color?,
        fontSize: TextUnit,
        fontWeight: FontWeight?,
        fontStyle: FontStyle?,
        letterSpacing: TextUnit,
        textDecoration: TextDecoration?,
    ) : this(
        PlatformSpanStyle(
            color = color.toPlatformValue(),
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
        )
    )

    actual val color: Color?
        get() = platformValue.color.let { if (it == PlatformColor.Unspecified) null else Color(it) }
    actual val fontSize: TextUnit get() = platformValue.fontSize
    actual val fontWeight: FontWeight? get() = platformValue.fontWeight
    actual val fontStyle: FontStyle? get() = platformValue.fontStyle
    actual val letterSpacing: TextUnit get() = platformValue.letterSpacing
    actual val textDecoration: TextDecoration? get() = platformValue.textDecoration
}
