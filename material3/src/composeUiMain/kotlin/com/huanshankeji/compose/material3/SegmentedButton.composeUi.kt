package com.huanshankeji.compose.material3

import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.huanshankeji.compose.foundation.layout.Row
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun SingleChoiceSegmentedButtonRow(
    modifier: Modifier,
    space: Dp?,
    content: @Composable SingleChoiceSegmentedButtonRowScope.() -> Unit
) =
    androidx.compose.material3.SingleChoiceSegmentedButtonRow(
        modifier.platformModifier,
        space ?: SegmentedButtonDefaults.BorderWidth
    ) { SingleChoiceSegmentedButtonRowScope(this).content() }

@Composable
actual fun MultiChoiceSegmentedButtonRow(
    modifier: Modifier,
    space: Dp?,
    content: @Composable MultiChoiceSegmentedButtonRowScope.() -> Unit
) =
    Row(modifier = modifier) {
        MultiChoiceSegmentedButtonRowScope().content()
    }

actual class SingleChoiceSegmentedButtonRowScope(val platformScope: androidx.compose.material3.SingleChoiceSegmentedButtonRowScope) {
    @Composable
    actual fun SegmentedButton(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier,
        enabled: Boolean,
        icon: @Composable (() -> Unit)?,
        label: @Composable () -> Unit
    ) =
        platformScope.SegmentedButton(
            selected,
            onClick,
            SegmentedButtonDefaults.itemShape(
                index = TODO(),
                count = TODO()
            ),
            modifier.platformModifier,
            enabled,
            icon = TODO(),
            label = label
        )
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
    ) =
        TODO() as Unit
}
