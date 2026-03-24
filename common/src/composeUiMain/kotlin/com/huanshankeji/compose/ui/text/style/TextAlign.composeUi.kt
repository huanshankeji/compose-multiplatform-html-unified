package com.huanshankeji.compose.ui.text.style

import androidx.compose.ui.text.style.TextAlign as PlatformTextAlign

fun TextAlign.toPlatformValue(): PlatformTextAlign =
    when (this) {
        TextAlign.Left -> PlatformTextAlign.Left
        TextAlign.Right -> PlatformTextAlign.Right
        TextAlign.Center -> PlatformTextAlign.Center
        TextAlign.Justify -> PlatformTextAlign.Justify
        TextAlign.Start -> PlatformTextAlign.Start
        TextAlign.End -> PlatformTextAlign.End
        else -> PlatformTextAlign.Unspecified
    }
