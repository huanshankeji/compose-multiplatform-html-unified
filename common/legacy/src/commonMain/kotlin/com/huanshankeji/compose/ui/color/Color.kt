package com.huanshankeji.compose.ui.color

expect class Color

internal fun UByte.alphaToFloatRatio(): Float =
    toFloat() / 255

internal fun Float.alphaToUByte(): UByte {
    require(this in 0f..1f)
    return (this * 255).toInt().toUByte()
}

expect fun rgbaColor(red: UByte, green: UByte, blue: UByte, alpha: UByte): Color
expect fun rgbaColor(red: UByte, green: UByte, blue: UByte, alpha: Float): Color

expect fun rgbColor(red: UByte, green: UByte, blue: UByte): Color
