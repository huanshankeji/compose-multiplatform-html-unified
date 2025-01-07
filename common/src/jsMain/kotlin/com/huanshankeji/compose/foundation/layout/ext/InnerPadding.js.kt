package com.huanshankeji.compose.foundation.layout.ext

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.foundation.layout.PaddingValuesImpl
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.unit.toPx
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.paddingInline

@Stable
actual fun Modifier.innerPadding(start: Dp, top: Dp, end: Dp, bottom: Dp): Modifier =
    platformModify {
        paddingInline(start.toPx(), end.toPx())
            .padding {
                top(top.toPx())
                bottom(bottom.toPx())
            }
    }

@Stable
actual fun Modifier.innerPadding(horizontal: Dp, vertical: Dp): Modifier =
    platformModify { padding(vertical.toPx(), horizontal.toPx()) }

@Stable
actual fun Modifier.innerPadding(all: Dp): Modifier =
    platformModify { padding(all.toPx()) }

@Stable
actual fun Modifier.innerPadding(paddingValues: PaddingValues): Modifier =
    when (paddingValues) {
        is PaddingValues.Absolute -> with(paddingValues) { absoluteInnerPadding(left, top, right, bottom) }
        is PaddingValuesImpl -> with(paddingValues) { innerPadding(start, top, end, bottom) }
        else -> throw IllegalArgumentException(paddingValues.toString())
    }

@Stable
actual fun Modifier.absoluteInnerPadding(left: Dp, top: Dp, right: Dp, bottom: Dp): Modifier =
    platformModify { padding(top.toPx(), right.toPx(), bottom.toPx(), left.toPx()) }
