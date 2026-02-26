package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdSlider
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.ext.onChange
import com.huanshankeji.compose.web.attributes.ext.onInput
import com.huanshankeji.compose.web.attributes.isFalseOrNull

/**
 * Calculate the step to match the Compose UI behavior.
 */
private fun calculateStep(steps: Int, valueRange: ClosedFloatingPointRange<Float>): Number =
    // JavaScript doesn't have a 32-bit float type so we just use `Double.MIN_VALUE` here.
    if (steps > 0) (valueRange.endInclusive - valueRange.start) / (steps + 1) else Double.MIN_VALUE

@Composable
actual fun Slider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    valueRange: ClosedFloatingPointRange<Float>,
    /*@IntRange(from = 0)*/ steps: Int,
    onValueChangeFinished: (() -> Unit)?
) =
    MdSlider(
        valueRange.start,
        valueRange.endInclusive,
        value,
        step = calculateStep(steps, valueRange),
        disabled = enabled.isFalseOrNull(),
        attrs = modifier.toAttrs {
            //https://github.com/material-components/material-web/blob/main/docs/components/slider.md#events

            onInput { event ->
                // MdSlider uses input event for value changes
                //console.log("Slider input event: ", event)
                val target = event.target.asDynamic()
                //console.log("`target.value` type: " + jsTypeOf(target.value)) // result: `number`
                val value = target.value as Float
                onValueChange(value)
            }

            onValueChangeFinished?.let { onChange { it() } }
        }
    )

@Composable
actual fun RangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    valueRange: ClosedFloatingPointRange<Float>,
    /*@IntRange(from = 0)*/ steps: Int,
    onValueChangeFinished: (() -> Unit)?
) =
    MdSlider(
        valueRange.start,
        valueRange.endInclusive,
        valueStart = value.start,
        valueEnd = value.endInclusive,
        step = calculateStep(steps, valueRange),
        range = true,
        disabled = enabled.isFalseOrNull(),
        attrs = modifier.toAttrs {
            onInput { event ->
                val target = event.target.asDynamic()
                //console.log("Range slider input event: ", event)
                val newStart = target.valueStart as Float
                val newEnd = target.valueEnd as Float
                onValueChange(newStart..newEnd)
            }

            onValueChangeFinished?.let { onChange { it() } }
        })
