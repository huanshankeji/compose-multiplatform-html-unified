package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.Row
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun SingleChoiceSegmentedButtonRow(
    modifier: Modifier,
    content: @Composable SingleChoiceSegmentedButtonRowScope.() -> Unit
) =
    Row(modifier = modifier) {
        SingleChoiceSegmentedButtonRowScope().content()
    }

@Composable
actual fun MultiChoiceSegmentedButtonRow(
    modifier: Modifier,
    content: @Composable MultiChoiceSegmentedButtonRowScope.() -> Unit
) =
    Row(modifier = modifier) {
        MultiChoiceSegmentedButtonRowScope().content()
    }

actual class SingleChoiceSegmentedButtonRowScope {
    @Composable
    actual fun SegmentedButton(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier,
        enabled: Boolean,
        icon: @Composable (() -> Unit)?,
        label: @Composable () -> Unit
    ) {
        if (selected) {
            FilledTonalButton(onClick = onClick, modifier = modifier, enabled = enabled) {
                icon?.invoke()
                label()
            }
        } else {
            OutlinedButton(onClick = onClick, modifier = modifier, enabled = enabled) {
                icon?.invoke()
                label()
            }
        }
    }
}

actual class MultiChoiceSegmentedButtonRowScope {
    @Composable
    actual fun SegmentedButton(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier,
        enabled: Boolean,
        icon: @Composable (() -> Unit)?,
        label: @Composable () -> Unit
    ) {
        if (selected) {
            FilledTonalButton(onClick = onClick, modifier = modifier, enabled = enabled) {
                icon?.invoke()
                label()
            }
        } else {
            OutlinedButton(onClick = onClick, modifier = modifier, enabled = enabled) {
                icon?.invoke()
                label()
            }
        }
    }
}
