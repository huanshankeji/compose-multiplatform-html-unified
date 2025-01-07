package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.foundation.layout.toPlatformRowScopeContent
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun FloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.FloatingActionButton(onClick, modifier.platformModifier, content = content)

@Composable
actual fun SmallFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.SmallFloatingActionButton(onClick, modifier.platformModifier, content = content)

@Composable
actual fun LargeFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.LargeFloatingActionButton(onClick, modifier.platformModifier, content = content)

@Composable
actual fun ExtendedFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) =
    androidx.compose.material3.ExtendedFloatingActionButton(
        onClick, modifier.platformModifier, content = content.toPlatformRowScopeContent()
    )
