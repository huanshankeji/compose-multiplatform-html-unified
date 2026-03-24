package com.huanshankeji.compose.ui.text

import com.huanshankeji.compose.ui.graphics.toPlatformValue
import com.huanshankeji.compose.ui.text.font.toPlatformValue
import com.huanshankeji.compose.ui.text.style.toPlatformValue
import androidx.compose.ui.text.SpanStyle as PlatformSpanStyle

fun SpanStyle.toPlatformValue(): PlatformSpanStyle =
    PlatformSpanStyle(
        color = color.toPlatformValue(),
        fontSize = fontSize,
        fontWeight = fontWeight?.toPlatformValue(),
        fontStyle = fontStyle?.toPlatformValue(),
        letterSpacing = letterSpacing,
        textDecoration = textDecoration?.toPlatformValue(),
    )
