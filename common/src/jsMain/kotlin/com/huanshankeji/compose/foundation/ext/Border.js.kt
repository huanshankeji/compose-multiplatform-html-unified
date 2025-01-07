package com.huanshankeji.compose.foundation.ext

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.BorderStroke
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.graphics.Brush
import com.huanshankeji.compose.ui.graphics.Color
import com.huanshankeji.compose.ui.unit.toPx
import com.varabyte.kobweb.compose.css.CSSLengthOrPercentageNumericValue
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.percent

@Stable
actual fun Modifier.outerBorder(border: BorderStroke): Modifier =
    platformModify { border(border.width.toPx(), border.brush.lineStyle, border.brush.color.platformValue) }

@Stable
actual fun Modifier.outerBorder(width: Dp, color: Color): Modifier =
    outerBorder(BorderStroke(width, Brush(LineStyle.Solid /* `null` produces no border */, color)))

private fun Modifier.borderRadius(borderRadius: CSSLengthOrPercentageNumericValue) =
    platformModify { borderRadius(borderRadius) }

@Stable
actual fun Modifier.roundedCornerOuterBorder(border: BorderStroke, borderRadius: Dp): Modifier =
    outerBorder(border).borderRadius(borderRadius.toPx())

@Stable
actual fun Modifier.roundedCornerOuterBorder(border: BorderStroke, borderRadiusPercent: Int): Modifier =
    outerBorder(border).borderRadius(borderRadiusPercent.percent)

@Stable
actual fun Modifier.roundedCornerOuterBorder(width: Dp, color: Color, borderRadius: Dp): Modifier =
    outerBorder(width, color).borderRadius(borderRadius.toPx())

@Stable
actual fun Modifier.roundedCornerOuterBorder(width: Dp, color: Color, borderRadiusPercent: Int): Modifier =
    outerBorder(width, color).borderRadius(borderRadiusPercent.percent)
