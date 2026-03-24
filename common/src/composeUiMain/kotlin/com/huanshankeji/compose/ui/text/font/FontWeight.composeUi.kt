package com.huanshankeji.compose.ui.text.font

import androidx.compose.ui.text.font.FontWeight as PlatformFontWeight

fun FontWeight.toPlatformValue(): PlatformFontWeight =
    PlatformFontWeight(weight)
