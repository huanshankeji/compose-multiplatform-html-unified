package com.huanshankeji.compose.material3

import androidx.annotation.IntRange
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Slider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    valueRange: ClosedFloatingPointRange<Float>,
    @IntRange(from = 0) steps: Int,
    onValueChangeFinished: (() -> Unit)?,
) =
    androidx.compose.material3.Slider(
        value,
        onValueChange,
        modifier.platformModifier,
        enabled,
        valueRange,
        steps,
        onValueChangeFinished,
    )

@Composable
actual fun RangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    valueRange: ClosedFloatingPointRange<Float>,
    @IntRange(from = 0) steps: Int,
    onValueChangeFinished: (() -> Unit)?,
) =
    androidx.compose.material3.RangeSlider(
        value,
        onValueChange,
        modifier.platformModifier,
        enabled,
        valueRange,
        steps,
        onValueChangeFinished,
    )
