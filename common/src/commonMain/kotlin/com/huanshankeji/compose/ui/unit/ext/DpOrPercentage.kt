package com.huanshankeji.compose.ui.unit.ext

import androidx.annotation.IntRange
import com.huanshankeji.compose.ExperimentalApi

@Deprecated("not used for now")
@ExperimentalApi
sealed class DpOrPercentage {
    class Dp(val dp: androidx.compose.ui.unit.Dp) : DpOrPercentage()
    class Percentage(@IntRange(0, 100) val percentage: Int) : DpOrPercentage()
}