package com.huanshankeji.compose.foundation.layout

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.layout.ext.absoluteOuterPadding
import com.huanshankeji.compose.foundation.layout.ext.outerPadding
import com.huanshankeji.compose.ui.Modifier

@Stable
actual fun Modifier.padding(start: Dp, top: Dp, end: Dp, bottom: Dp): Modifier =
    outerPadding(start, top, end, bottom)

@Stable
actual fun Modifier.padding(horizontal: Dp, vertical: Dp): Modifier =
    outerPadding(horizontal, vertical)

@Stable
actual fun Modifier.padding(all: Dp): Modifier =
    outerPadding(all)

@Stable
actual fun Modifier.padding(paddingValues: PaddingValues): Modifier =
    outerPadding(paddingValues)

@Stable
actual fun Modifier.absolutePadding(left: Dp, top: Dp, right: Dp, bottom: Dp): Modifier =
    absoluteOuterPadding(left, top, right, bottom)

// copied and adapted from `androidx.compose.foundation.layout.Padding.kt`

@Stable
actual abstract class PaddingValues internal constructor() {
    @Immutable
    actual class Absolute actual constructor(
        @Stable
        val left: Dp,
        @Stable
        val top: Dp,
        @Stable
        val right: Dp,
        @Stable
        val bottom: Dp
    ) : PaddingValues()
}

@Stable
actual fun PaddingValues(all: Dp): PaddingValues =
    PaddingValuesImpl(all, all, all, all)

@Stable
actual fun PaddingValues(horizontal: Dp, vertical: Dp): PaddingValues =
    PaddingValuesImpl(horizontal, vertical, horizontal, vertical)

@Stable
actual fun PaddingValues(start: Dp, top: Dp, end: Dp, bottom: Dp): PaddingValues =
    PaddingValuesImpl(start, top, end, bottom)

@Immutable
internal class PaddingValuesImpl(
    @Stable
    val start: Dp,
    @Stable
    val top: Dp,
    @Stable
    val end: Dp,
    @Stable
    val bottom: Dp
) : PaddingValues()
