package com.huanshankeji.compose.ui.text.font

import androidx.compose.ui.text.font.FontStyle as PlatformFontStyle

fun FontStyle.toPlatformValue(): PlatformFontStyle =
    when (this) {
        FontStyle.Normal -> PlatformFontStyle.Normal
        FontStyle.Italic -> PlatformFontStyle.Italic
        else -> PlatformFontStyle.Normal
    }
