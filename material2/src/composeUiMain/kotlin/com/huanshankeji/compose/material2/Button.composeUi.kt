package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.foundation.layout.toPlatformRowScopeContent
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun Button(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) =
    androidx.compose.material.Button(onClick, modifier.platformModifier, content = content.toPlatformRowScopeContent())

@Composable
actual fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) =
    androidx.compose.material.OutlinedButton(
        onClick,
        modifier.platformModifier,
        content = content.toPlatformRowScopeContent()
    )

@Composable
actual fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) =
    androidx.compose.material.TextButton(
        onClick,
        modifier.platformModifier,
        content = content.toPlatformRowScopeContent()
    )
