package com.huanshankeji.compose.material3

import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
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
    androidx.compose.material3.MultiChoiceSegmentedButtonRow(
        modifier.platformModifier,
        space ?: SegmentedButtonDefaults.BorderWidth
    ) {
        MultiChoiceSegmentedButtonRowScope(this).content()
    }

actual class SingleChoiceSegmentedButtonRowScope(val platformScope: androidx.compose.material3.SingleChoiceSegmentedButtonRowScope) {
    @Composable
    actual fun SegmentedButton(
        selected: Boolean,
        onClick: () -> Unit,
        defaultShapeArgs: SegmentedButtonDefaultShapeArgs,
        modifier: Modifier,
        enabled: Boolean,
        icon: @Composable (() -> Unit)?,
        label: String //@Composable () -> Unit
    ) =
        platformScope.SegmentedButton(
            selected,
            onClick,
            SegmentedButtonDefaults.itemShape(
                defaultShapeArgs.index,
                defaultShapeArgs.count
            ),
            modifier.platformModifier,
            enabled,
            icon = icon ?: { SegmentedButtonDefaults.Icon(selected) },
        ) {
            Text(label)
        }
}

actual class MultiChoiceSegmentedButtonRowScope(val platformScope: androidx.compose.material3.MultiChoiceSegmentedButtonRowScope) {
    @Composable
    actual fun SegmentedButton(
        checked: Boolean,
        onCheckedChange: (Boolean) -> Unit,
        defaultShapeArgs: SegmentedButtonDefaultShapeArgs,
        modifier: Modifier,
        enabled: Boolean,
        icon: @Composable (() -> Unit)?,
        label: String //@Composable () -> Unit
    ) =
        platformScope.SegmentedButton(
            checked,
            onCheckedChange,
            SegmentedButtonDefaults.itemShape(
                defaultShapeArgs.index,
                defaultShapeArgs.count
            ),
            modifier.platformModifier,
            enabled,
            icon = icon ?: { SegmentedButtonDefaults.Icon(checked) },
        ) {
            Text(label)
        }
}
