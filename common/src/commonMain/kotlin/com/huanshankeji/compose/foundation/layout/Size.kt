package com.huanshankeji.compose.foundation.layout

import androidx.annotation.FloatRange
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.huanshankeji.compose.ExperimentalApi
import com.huanshankeji.compose.LikelyInconsistentApi
import com.huanshankeji.compose.foundation.layout.ext.fillMaxHeightStretch
import com.huanshankeji.compose.foundation.layout.ext.fillMaxSizeStretch
import com.huanshankeji.compose.foundation.layout.ext.fillMaxWidthStretch
import com.huanshankeji.compose.ui.Modifier

@Stable
expect fun Modifier.width(width: Dp): Modifier

@Stable
expect fun Modifier.height(height: Dp): Modifier

@Stable
expect fun Modifier.size(dp: Dp): Modifier

@Stable
expect fun Modifier.size(width: Dp, height: Dp): Modifier

@Stable
fun Modifier.size(size: DpSize) =
    size(size.width, size.height)

@Stable
expect fun Modifier.widthIn(min: Dp = Dp.Unspecified, max: Dp = Dp.Unspecified): Modifier

@Stable
expect fun Modifier.heightIn(min: Dp = Dp.Unspecified, max: Dp = Dp.Unspecified): Modifier

@Stable
expect fun Modifier.sizeIn(
    minWidth: Dp = Dp.Unspecified,
    minHeight: Dp = Dp.Unspecified,
    maxWidth: Dp = Dp.Unspecified,
    maxHeight: Dp = Dp.Unspecified
): Modifier

/**
 * @see fillMaxWidthStretch
 */
@Stable
expect fun Modifier.fillMaxWidth(@FloatRange(from = 0.0, to = 1.0) fraction: Float = 1f): Modifier

/**
 * @see fillMaxHeightStretch
 */
@Stable
expect fun Modifier.fillMaxHeight(@FloatRange(from = 0.0, to = 1.0) fraction: Float = 1f): Modifier

/**
 * @see fillMaxSizeStretch
 */
@Stable
expect fun Modifier.fillMaxSize(@FloatRange(from = 0.0, to = 1.0) fraction: Float = 1f): Modifier

@ExperimentalApi
@LikelyInconsistentApi
@Stable
expect fun Modifier.wrapContentWidth(): Modifier

@ExperimentalApi
@LikelyInconsistentApi
@Stable
expect fun Modifier.wrapContentHeight(): Modifier

@ExperimentalApi
@LikelyInconsistentApi
@Stable
expect fun Modifier.wrapContentSize(): Modifier
