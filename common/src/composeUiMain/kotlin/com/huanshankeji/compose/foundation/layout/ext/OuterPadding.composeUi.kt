package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.foundation.layout.absolutePadding
import com.huanshankeji.compose.foundation.layout.padding
import com.huanshankeji.compose.ui.Modifier

@Stable
actual fun Modifier.outerPadding(start: Dp, top: Dp, end: Dp, bottom: Dp): Modifier =
    padding(start, top, end, bottom)

@Stable
actual fun Modifier.outerPadding(horizontal: Dp, vertical: Dp): Modifier =
    padding(horizontal, vertical)

@Stable
actual fun Modifier.outerPadding(all: Dp): Modifier =
    padding(all)

@Stable
actual fun Modifier.outerPadding(paddingValues: PaddingValues): Modifier =
    padding(paddingValues)

@Stable
actual fun Modifier.absoluteOuterPadding(left: Dp, top: Dp, right: Dp, bottom: Dp): Modifier =
    absolutePadding(left, top, right, bottom)
