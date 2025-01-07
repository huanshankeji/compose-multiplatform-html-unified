package com.huanshankeji.compose.foundation.ext

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.BorderStroke
import com.huanshankeji.compose.foundation.border
import com.huanshankeji.compose.foundation.layout.padding
import com.huanshankeji.compose.foundation.toPlatformValue
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color

/** Follows the default CSS `border` behavior that is on the outside. */
@Stable
actual fun Modifier.outerBorder(border: BorderStroke): Modifier =
    border(border).padding(border.width)

@Stable
actual fun Modifier.outerBorder(width: Dp, color: Color): Modifier =
    border(width, color).padding(width)

@Stable
actual fun Modifier.roundedCornerOuterBorder(border: BorderStroke, borderRadius: Dp): Modifier =
    platformModify { border(border.toPlatformValue(), RoundedCornerShape(borderRadius)).padding(border.width) }

@Stable
actual fun Modifier.roundedCornerOuterBorder(border: BorderStroke, borderRadiusPercent: Int): Modifier =
    platformModify { border(border.toPlatformValue(), RoundedCornerShape(borderRadiusPercent)).padding(border.width) }

@Stable
actual fun Modifier.roundedCornerOuterBorder(width: Dp, color: Color, borderRadius: Dp): Modifier =
    platformModify { border(width, color.platformValue, RoundedCornerShape(borderRadius)).padding(width) }

@Stable
actual fun Modifier.roundedCornerOuterBorder(width: Dp, color: Color, borderRadiusPercent: Int): Modifier =
    platformModify { border(width, color.platformValue, RoundedCornerShape(borderRadiusPercent)).padding(width) }
