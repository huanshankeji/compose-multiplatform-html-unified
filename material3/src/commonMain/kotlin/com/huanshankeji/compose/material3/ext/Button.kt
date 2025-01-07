package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

/**
 * filled button
 * @param icon the [Modifier] parameter contains the attributes to be set on this icon on JS. You are supposed to pass this [Modifier] to the top-level composable that you invoke inside.
 */
@Composable
expect fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable ((Modifier) -> Unit)? = null,
    isTrailingIcon: Boolean = false,
    content: @Composable () -> Unit
)

@Composable
fun ButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: Icon?,
    isTrailingIcon: Boolean = false,
    content: @Composable () -> Unit
) =
    Button(
        onClick,
        modifier,
        enabled,
        icon.toNullableContentWithModifier(),
        isTrailingIcon,
        content
    )

/**
 * a shortcut to `Button`
 */
@Composable
fun FilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable ((Modifier) -> Unit)? = null,
    isTrailingIcon: Boolean = false,
    content: @Composable () -> Unit
) =
    Button(onClick, modifier, enabled, icon, isTrailingIcon, content)

@Composable
expect fun ElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable ((Modifier) -> Unit)? = null,
    isTrailingIcon: Boolean = false,
    content: @Composable () -> Unit
)

@Composable
fun ElevatedButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: Icon?,
    isTrailingIcon: Boolean = false,
    content: @Composable () -> Unit
) =
    ElevatedButton(
        onClick,
        modifier,
        enabled,
        icon.toNullableContentWithModifier(),
        isTrailingIcon,
        content
    )

@Composable
expect fun FilledTonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable ((Modifier) -> Unit)? = null,
    isTrailingIcon: Boolean = false,
    content: @Composable () -> Unit
)

@Composable
fun FilledTonalButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: Icon?,
    isTrailingIcon: Boolean = false,
    content: @Composable () -> Unit
) =
    FilledTonalButton(
        onClick,
        modifier,
        enabled,
        icon.toNullableContentWithModifier(),
        isTrailingIcon,
        content
    )

@Composable
expect fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable ((Modifier) -> Unit)? = null,
    isTrailingIcon: Boolean = false,
    content: @Composable () -> Unit
)

@Composable
fun OutlinedButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: Icon?,
    isTrailingIcon: Boolean = false,
    content: @Composable () -> Unit
) =
    OutlinedButton(
        onClick,
        modifier,
        enabled,
        icon.toNullableContentWithModifier(),
        isTrailingIcon,
        content
    )

@Composable
expect fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable ((Modifier) -> Unit)? = null,
    isTrailingIcon: Boolean = false,
    content: @Composable () -> Unit
)

@Composable
fun TextButtonWithMaterialIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: Icon?,
    isTrailingIcon: Boolean = false,
    content: @Composable () -> Unit
) =
    TextButton(
        onClick,
        modifier,
        enabled,
        icon.toNullableContentWithModifier(),
        isTrailingIcon,
        content
    )
