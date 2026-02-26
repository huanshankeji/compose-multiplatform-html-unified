package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdSlider
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.ext.onInput
import com.huanshankeji.compose.web.attributes.isFalseOrNull

/**
 * Calculate the step to match the Compose UI behavior.
 */
private fun calculateStep(steps: Int, valueRange: ClosedFloatingPointRange<Float>): Float =
    // We can also use `Double.MIN_VALUE` and change the return type to `Number`, but `Float.MIN_VALUE` should be sufficient here.
    if (steps > 0) (valueRange.endInclusive - valueRange.start) / (steps + 1) else Float.MIN_VALUE

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
            onInput { event ->
                // MdSlider uses input event for value changes
                //console.log("Slider input event: ", event)
                val target = event.target.asDynamic()
                //console.log("`target.value` type: " + jsTypeOf(target.value)) // result: `number`
                val value = target.value as Float
                onValueChange(value)
            }
            // Note: onValueChangeFinished is not currently supported in the JS implementation
            // TODO: Add support for onValueChangeFinished using appropriate event listener
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
        })
