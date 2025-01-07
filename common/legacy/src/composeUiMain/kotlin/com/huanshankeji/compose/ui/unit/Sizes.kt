package com.huanshankeji.compose.ui.unit

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

actual sealed interface LengthOrPercentage
actual sealed interface Length : LengthOrPercentage
actual typealias Percentage = PercentageImpl

class PercentageImpl(val value: Int) : LengthOrPercentage {
    init {
        require(value in 0..100)
    }
}

actual val Int.percent get() = Percentage(this)

actual class DpOrPx(val platformValue: Dp) : Length

fun Length.asDpOrPx(): DpOrPx =
    when (this) {
        is DpOrPx -> this
        // TODO: this else branch is not needed but marked as an error by the IDE plugin
        else -> throw AssertionError()
    }

fun LengthOrPercentage.asDpOrPx(): DpOrPx =
    when (this) {
        is DpOrPx -> this
        // TODO
        else -> throw AssertionError()
    }

actual val Int.dpOrPx: DpOrPx
    get() = DpOrPx(this.dp)
