package com.huanshankeji.compose.ui.text.style

import androidx.compose.ui.text.style.TextOverflow as PlatformTextOverflow

fun TextOverflow.toPlatformValue(): PlatformTextOverflow =
    when (this) {
        TextOverflow.Clip -> PlatformTextOverflow.Clip
        TextOverflow.Ellipsis -> PlatformTextOverflow.Ellipsis
        TextOverflow.Visible -> PlatformTextOverflow.Visible
        else -> PlatformTextOverflow.Clip
    }
