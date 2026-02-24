package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdSlider
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.ext.disabled
import com.huanshankeji.compose.web.attributes.ext.onInput
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event

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
    MdSlider(
        min = valueRange.start,
        max = valueRange.endInclusive,
        value = value,
        step = if (steps > 0) (valueRange.endInclusive - valueRange.start) / (steps + 1) else null,
        attrs = modifier.toAttrs {
            disabled(enabled.isFalseOrNull())
            onInput { event ->
                // MdSlider uses input event for value changes
                val target = event.target.asDynamic()
                val newValue = (target.value as? Number)?.toFloat() ?: (target.value as? String)?.toFloatOrNull()
                newValue?.let { onValueChange(it) }
            }
            // Note: onValueChangeFinished is not currently supported in the JS implementation
            // TODO: Add support for onValueChangeFinished using appropriate event listener
        }
    )
