package com.huanshankeji.compose.material3.ext

import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material3.MultiChoiceSegmentedButtonRowScope
import com.huanshankeji.compose.material3.SegmentedButtonDefaultShapeArgs
import com.huanshankeji.compose.material3.SingleChoiceSegmentedButtonRowScope
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun SingleChoiceSegmentedButtonRowScope.SegmentedButton(
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

@Composable
actual fun MultiChoiceSegmentedButtonRowScope.SegmentedButton(
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
