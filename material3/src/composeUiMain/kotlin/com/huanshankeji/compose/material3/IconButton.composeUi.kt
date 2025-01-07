package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.IconButton(onClick, modifier.platformModifier, enabled, content = content)

@Composable
actual fun IconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.IconToggleButton(
        checked,
        onCheckedChange,
        modifier.platformModifier,
        enabled,
        content = content
    )

@Composable
actual fun FilledIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.FilledIconButton(onClick, modifier.platformModifier, enabled, content = content)

@Composable
actual fun FilledIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.FilledIconToggleButton(
        checked,
        onCheckedChange,
        modifier.platformModifier,
        enabled,
        content = content
    )

@Composable
actual fun FilledTonalIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.FilledTonalIconButton(onClick, modifier.platformModifier, enabled, content = content)

@Composable
actual fun FilledTonalIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.FilledTonalIconToggleButton(
        checked,
        onCheckedChange,
        modifier.platformModifier,
        enabled,
        content = content
    )

@Composable
actual fun OutlinedIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.OutlinedIconButton(onClick, modifier.platformModifier, enabled, content = content)

@Composable
actual fun OutlinedIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.OutlinedIconToggleButton(
        checked,
        onCheckedChange,
        modifier.platformModifier,
        enabled,
        content = content
    )
