package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.material3.SingleChoiceSegmentedButtonRow as ComposeSingleChoiceSegmentedButtonRow
import androidx.compose.material3.MultiChoiceSegmentedButtonRow as ComposeMultiChoiceSegmentedButtonRow

@Composable
actual fun SingleChoiceSegmentedButtonRow(
    modifier: Modifier,
    content: @Composable SegmentedButtonRowScope.() -> Unit
) =
    ComposeSingleChoiceSegmentedButtonRow(
        modifier = modifier.platformModifier
    ) {
        SegmentedButtonRowScope(this).content()
    }

@Composable
actual fun MultiChoiceSegmentedButtonRow(
    modifier: Modifier,
    content: @Composable SegmentedButtonRowScope.() -> Unit
) =
    ComposeMultiChoiceSegmentedButtonRow(
        modifier = modifier.platformModifier
    ) {
        SegmentedButtonRowScope(this).content()
    }

actual class SegmentedButtonRowScope(
    private val composeScope: androidx.compose.material3.SegmentedButtonScope
) {
    @Composable
    actual fun SegmentedButton(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier,
        enabled: Boolean,
        icon: @Composable (() -> Unit)?,
        label: @Composable () -> Unit
    ) {
        with(composeScope) {
            androidx.compose.material3.SegmentedButton(
                selected = selected,
                onClick = onClick,
                modifier = modifier.platformModifier,
                enabled = enabled,
                icon = icon,
                label = label
            )
        }
    }
}
