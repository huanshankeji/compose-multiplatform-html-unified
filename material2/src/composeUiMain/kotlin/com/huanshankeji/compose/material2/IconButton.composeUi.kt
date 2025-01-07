package com.huanshankeji.compose.material2

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    androidx.compose.material.IconButton(onClick, modifier.platformModifier, content = content)

/*
@Composable
actual fun IconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    androidx.compose.material.IconToggleButton(checked, onCheckedChange, modifier.platformModifier, content = content)
*/
