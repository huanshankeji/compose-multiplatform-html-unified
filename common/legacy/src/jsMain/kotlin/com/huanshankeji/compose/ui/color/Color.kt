package com.huanshankeji.compose.ui.color

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgb
import org.jetbrains.compose.web.css.rgba

actual class Color(val platformValue: CSSColorValue)

actual fun rgbaColor(red: UByte, green: UByte, blue: UByte, alpha: UByte): Color =
    rgbaColor(red, green, blue, alpha.alphaToFloatRatio())

actual fun rgbaColor(red: UByte, green: UByte, blue: UByte, alpha: Float): Color =
    Color(rgba(red.toInt(), green.toInt(), blue.toInt(), alpha))

actual fun rgbColor(red: UByte, green: UByte, blue: UByte): Color =
    Color(rgb(red.toInt(), green.toInt(), blue.toInt()))
