package com.huanshankeji.compose.foundation

fun BorderStroke.toPlatformValue() =
    androidx.compose.foundation.BorderStroke(width, brush.platformValue)
