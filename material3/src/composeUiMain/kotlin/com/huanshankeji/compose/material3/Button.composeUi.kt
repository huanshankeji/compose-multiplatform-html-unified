package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.foundation.layout.toPlatformRowScopeContent
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Button(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable RowScope.() -> Unit
) =
    androidx.compose.material3.Button(
        onClick, modifier.platformModifier, enabled, content = content.toPlatformRowScopeContent()
    )

@Composable
actual fun ElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable RowScope.() -> Unit
) =
    androidx.compose.material3.ElevatedButton(
        onClick, modifier.platformModifier, enabled, content = content.toPlatformRowScopeContent()
    )

@Composable
actual fun FilledTonalButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable RowScope.() -> Unit
) =
    androidx.compose.material3.FilledTonalButton(
        onClick, modifier.platformModifier, enabled, content = content.toPlatformRowScopeContent()
    )

@Composable
actual fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable RowScope.() -> Unit
) =
    androidx.compose.material3.OutlinedButton(
        onClick, modifier.platformModifier, enabled, content = content.toPlatformRowScopeContent()
    )

@Composable
actual fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    content: @Composable RowScope.() -> Unit
) =
    androidx.compose.material3.TextButton(
        onClick, modifier.platformModifier, enabled, content = content.toPlatformRowScopeContent()
    )
