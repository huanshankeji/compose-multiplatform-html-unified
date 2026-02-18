package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.platform.platformModifier

@Composable
actual fun Slider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    onValueChangeFinished: (() -> Unit)?
) =
    androidx.compose.material3.Slider(
        value,
        onValueChange,
        modifier.platformModifier,
        enabled,
        valueRange,
        steps,
        onValueChangeFinished
    )
