package com.huanshankeji.compose.ui.color

import androidx.compose.ui.graphics.Color as PlatformColor

typealias PlatformColor = androidx.compose.ui.graphics.Color

actual class Color(val platformValue: PlatformColor)

actual fun rgbaColor(red: UByte, green: UByte, blue: UByte, alpha: UByte): Color =
    Color(PlatformColor(red.toInt(), green.toInt(), blue.toInt(), alpha.toInt()))

actual fun rgbaColor(red: UByte, green: UByte, blue: UByte, alpha: Float): Color =
    rgbaColor(red, green, blue, alpha.alphaToUByte())

actual fun rgbColor(red: UByte, green: UByte, blue: UByte): Color =
    Color(PlatformColor(red.toInt(), green.toInt(), blue.toInt()))
