package com.huanshankeji.compose.foundation.ext

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.BorderStroke
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color

/** The border is not on top of the background. */
@Stable
expect fun Modifier.roundedCornerBackgroundAndOuterBorder(
    border: BorderStroke, cornerRadius: Dp, backgroundColor: Color
): Modifier

@Stable
expect fun Modifier.roundedCornerBackgroundAndOuterBorder(
    border: BorderStroke, cornerRadiusPercent: Int, backgroundColor: Color
): Modifier

@Stable
expect fun Modifier.roundedCornerBackgroundAndOuterBorder(
    width: Dp, borderColor: Color, cornerRadius: Dp, backgroundColor: Color
): Modifier

@Stable
expect fun Modifier.roundedCornerBackgroundAndOuterBorder(
    width: Dp, borderColor: Color, cornerRadiusPercent: Int, backgroundColor: Color
): Modifier

/*
/** The border is on top of the background. */
@Stable
expect fun Modifier.roundedCornerBackgroundAndOuterBorder()
*/

