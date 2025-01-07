package com.huanshankeji.compose.foundation.layout

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.foundation.layout.IntrinsicSize as PlatformIntrinsicSize

@Stable
actual fun Modifier.width(intrinsicSize: IntrinsicSize): Modifier =
    platformModify { width(intrinsicSize.toPlatformValue()) }

@Stable
actual fun Modifier.height(intrinsicSize: IntrinsicSize): Modifier =
    platformModify { height(intrinsicSize.toPlatformValue()) }

fun IntrinsicSize.toPlatformValue() =
    when (this) {
        IntrinsicSize.Min -> PlatformIntrinsicSize.Min
        IntrinsicSize.Max -> PlatformIntrinsicSize.Max
    }
