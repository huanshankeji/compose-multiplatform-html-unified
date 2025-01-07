package com.huanshankeji.compose.foundation.ext

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.BorderStroke
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color

/** Follows the default CSS `border` behavior that is on the outside. */
@Stable
expect fun Modifier.outerBorder(border: BorderStroke): Modifier

/** Follows the default CSS `border` behavior that is on the outside. */
@Stable
expect fun Modifier.outerBorder(width: Dp, color: Color): Modifier

@Stable
expect fun Modifier.roundedCornerOuterBorder(border: BorderStroke, borderRadius: Dp): Modifier

@Stable
expect fun Modifier.roundedCornerOuterBorder(border: BorderStroke, borderRadiusPercent: Int): Modifier

@Stable
expect fun Modifier.roundedCornerOuterBorder(width: Dp, color: Color, borderRadius: Dp): Modifier

@Stable
expect fun Modifier.roundedCornerOuterBorder(width: Dp, color: Color, borderRadiusPercent: Int): Modifier
