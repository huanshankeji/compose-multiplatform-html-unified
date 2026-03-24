package com.huanshankeji.compose.ui.text.style

import androidx.compose.ui.text.style.TextDecoration as PlatformTextDecoration

fun TextDecoration.toPlatformValue(): PlatformTextDecoration =
    when {
        this == TextDecoration.None -> PlatformTextDecoration.None
        this == TextDecoration.Underline -> PlatformTextDecoration.Underline
        this == TextDecoration.LineThrough -> PlatformTextDecoration.LineThrough
        else -> PlatformTextDecoration.combine(
            buildList {
                if (TextDecoration.Underline in this@toPlatformValue) add(PlatformTextDecoration.Underline)
                if (TextDecoration.LineThrough in this@toPlatformValue) add(PlatformTextDecoration.LineThrough)
            }
        )
    }
