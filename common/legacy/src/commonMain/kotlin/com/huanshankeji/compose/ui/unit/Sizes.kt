package com.huanshankeji.compose.ui.unit

// Percentage is only supported on JS.
expect sealed interface LengthOrPercentage
expect sealed interface Length : LengthOrPercentage
expect class Percentage : LengthOrPercentage

expect val Int.percent: Percentage

expect class DpOrPx : Length

expect val Int.dpOrPx: DpOrPx


sealed class HeightOrWidth {
    class Numeric(val value: LengthOrPercentage) : HeightOrWidth()
    object FillMax : HeightOrWidth()
    object FitContent : HeightOrWidth()
}
