package com.huanshankeji.compose.foundation.layout

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.ui.Modifier

@Stable
actual fun Modifier.width(width: Dp): Modifier =
    platformModify { width(width) }

@Stable
actual fun Modifier.height(height: Dp): Modifier =
    platformModify { height(height) }

@Stable
actual fun Modifier.size(dp: Dp) =
    platformModify { size(dp) }

@Stable
actual fun Modifier.size(width: Dp, height: Dp): Modifier =
    platformModify { size(width, height) }

@Stable
actual fun Modifier.widthIn(min: Dp, max: Dp): Modifier =
    platformModify { widthIn(min, max) }

@Stable
actual fun Modifier.heightIn(min: Dp, max: Dp): Modifier =
    platformModify { heightIn(min, max) }

@Stable
actual fun Modifier.sizeIn(
    minWidth: Dp, minHeight: Dp, maxWidth: Dp, maxHeight: Dp
): Modifier =
    platformModify { sizeIn(minWidth, minHeight, maxWidth, maxHeight) }

@Stable
actual fun Modifier.fillMaxWidth(@FloatRange(from = 0.0, to = 1.0) fraction: Float): Modifier =
    platformModify { fillMaxWidth(fraction) }

@Stable
actual fun Modifier.fillMaxHeight(@FloatRange(from = 0.0, to = 1.0) fraction: Float): Modifier =
    platformModify { fillMaxHeight(fraction) }

@Stable
actual fun Modifier.fillMaxSize(@FloatRange(from = 0.0, to = 1.0) fraction: Float): Modifier =
    platformModify { fillMaxSize(fraction) }

@Stable
actual fun Modifier.wrapContentWidth(): Modifier =
    platformModify { wrapContentWidth() }

@Stable
actual fun Modifier.wrapContentHeight(): Modifier =
    platformModify { wrapContentHeight() }

@Stable
actual fun Modifier.wrapContentSize(): Modifier =
    platformModify { wrapContentSize() }
