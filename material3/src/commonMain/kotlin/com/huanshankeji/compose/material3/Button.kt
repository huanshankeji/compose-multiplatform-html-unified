package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.RowScope
import com.huanshankeji.compose.ui.Modifier

/**
 * Filled button
 */
@Composable
expect fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    /*
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    */
    content: @Composable RowScope.() -> Unit
)

@Deprecated(
    "This is a shortcut to `Button`. Use `Button` instead.",
    ReplaceWith("Button(onClick, modifier, enabled, content)", "com.huanshankeji.compose.material3.Button")
)
@Composable
fun FilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) =
    Button(onClick, modifier, enabled, content)

@Composable
expect fun ElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
)

@Composable
expect fun FilledTonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
)

@Composable
expect fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
)

@Composable
expect fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
)
