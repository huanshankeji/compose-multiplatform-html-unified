package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

private fun commonIconToggleButtonContent(
    checked: Boolean,
    checkedContent: @Composable (Modifier) -> Unit,
    uncheckedContent: @Composable () -> Unit
): @Composable () -> Unit = {
    if (checked) checkedContent(Modifier) else uncheckedContent()
}

@Composable
actual fun IconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
) =
    com.huanshankeji.compose.material3.IconToggleButton(
        checked,
        onCheckedChange,
        modifier,
        enabled,
        commonIconToggleButtonContent(checked, checkedContent, uncheckedContent)
    )

@Composable
actual fun FilledIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
) =
    com.huanshankeji.compose.material3.FilledIconToggleButton(
        checked,
        onCheckedChange,
        modifier,
        enabled,
        commonIconToggleButtonContent(checked, checkedContent, uncheckedContent)
    )

@Composable
actual fun FilledTonalIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
) =
    com.huanshankeji.compose.material3.FilledTonalIconToggleButton(
        checked,
        onCheckedChange,
        modifier,
        enabled,
        commonIconToggleButtonContent(checked, checkedContent, uncheckedContent)
    )

@Composable
actual fun OutlinedIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
) =
    com.huanshankeji.compose.material3.OutlinedIconToggleButton(
        checked,
        onCheckedChange,
        modifier,
        enabled,
        commonIconToggleButtonContent(checked, checkedContent, uncheckedContent)
    )
