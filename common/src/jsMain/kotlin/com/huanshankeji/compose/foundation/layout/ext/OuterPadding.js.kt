package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.foundation.layout.PaddingValuesImpl
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.unit.toPx
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.marginInline

@Stable
actual fun Modifier.outerPadding(start: Dp, top: Dp, end: Dp, bottom: Dp): Modifier =
    platformModify {
        marginInline(start.toPx(), end.toPx())
            .margin {
                top(top.toPx())
                bottom(bottom.toPx())
            }
    }

@Stable
actual fun Modifier.outerPadding(horizontal: Dp, vertical: Dp): Modifier =
    platformModify { margin(vertical.toPx(), horizontal.toPx()) }

@Stable
actual fun Modifier.outerPadding(all: Dp): Modifier =
    platformModify { margin(all.toPx()) }

@Stable
actual fun Modifier.outerPadding(paddingValues: PaddingValues): Modifier =
    when (paddingValues) {
        is PaddingValues.Absolute -> with(paddingValues) { absoluteOuterPadding(left, top, right, bottom) }
        is PaddingValuesImpl -> with(paddingValues) { outerPadding(start, top, end, bottom) }
        else -> throw IllegalArgumentException(paddingValues.toString())
    }

@Stable
actual fun Modifier.absoluteOuterPadding(left: Dp, top: Dp, right: Dp, bottom: Dp): Modifier =
    platformModify { margin(top.toPx(), right.toPx(), bottom.toPx(), left.toPx()) }
