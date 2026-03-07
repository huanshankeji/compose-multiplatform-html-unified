package com.huanshankeji.compose.foundation.ext

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.BorderStroke
import com.huanshankeji.compose.foundation.background
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Color

@Stable
actual fun Modifier.roundedCornerBackgroundAndOuterBorder(
    border: BorderStroke, cornerRadius: Dp, backgroundColor: Color
): Modifier =
    background(backgroundColor).roundedCornerOuterBorder(border, cornerRadius)

@Stable
actual fun Modifier.roundedCornerBackgroundAndOuterBorder(
    border: BorderStroke, cornerRadiusPercent: Int, backgroundColor: Color
): Modifier =
    background(backgroundColor).roundedCornerOuterBorder(border, cornerRadiusPercent)

@Stable
actual fun Modifier.roundedCornerBackgroundAndOuterBorder(
    width: Dp, borderColor: Color, cornerRadius: Dp, backgroundColor: Color
): Modifier =
    background(backgroundColor).roundedCornerOuterBorder(width, borderColor, cornerRadius)

@Stable
actual fun Modifier.roundedCornerBackgroundAndOuterBorder(
    width: Dp, borderColor: Color, cornerRadiusPercent: Int, backgroundColor: Color
): Modifier =
    background(backgroundColor).roundedCornerOuterBorder(width, borderColor, cornerRadiusPercent)
