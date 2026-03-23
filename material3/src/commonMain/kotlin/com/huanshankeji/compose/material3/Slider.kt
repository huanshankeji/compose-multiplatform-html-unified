package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/*
https://developer.android.com/develop/ui/compose/components/slider
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-slider.html
 */

/**
 * Material Design slider.
 *
 * @see <a href="https://m3.material.io/components/sliders/overview">Material Design slider</a>
 * @see androidx.compose.material3.Slider
 */
@Composable
expect fun Slider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    /*@IntRange(from = 0)*/ steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
)

@Composable
expect fun RangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    /*@IntRange(from = 0)*/ steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
)
