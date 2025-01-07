package com.huanshankeji.compose.foundation

import androidx.compose.foundation.border
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color

@Stable
actual fun Modifier.border(border: BorderStroke): Modifier =
    platformModify { border(border.toPlatformValue()) }

@Stable
actual fun Modifier.border(width: Dp, color: Color): Modifier =
    platformModify { border(width, color.platformValue) }

/*
@Stable
actual fun Modifier.border(width: Dp, brush: Brush): Modifier =
    platformModify { border(width, brush.platformValue, RectangleShape) }
*/
