package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material3.Icon
import com.huanshankeji.compose.ui.Modifier

private fun Icon.toUncheckedIconContent(): @Composable () -> Unit = {
    Icon(this, null)
}

private fun Icon.toCheckedIconContent(): @Composable (Modifier) -> Unit =
    toContentWithModifier()

/**
 * @param checkedContent the [Modifier] parameter contains the attributes to be set on this icon on JS. You are supposed to pass this [Modifier] to the top-level composable that you invoke inside.
 */
@Composable
expect fun IconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
)

@Composable
fun IconToggleButtonWithMaterialIcons(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    uncheckedIcon: Icon,
    checkedIcon: Icon
) =
    IconToggleButton(
        checked,
        onCheckedChange,
        modifier,
        enabled,
        uncheckedIcon.toUncheckedIconContent(),
        checkedIcon.toCheckedIconContent()
    )

/**
 * @param checkedContent the [Modifier] parameter contains the attributes to be set on this icon on JS. You are supposed to pass this [Modifier] to the top-level composable that you invoke inside.
 */
@Composable
expect fun FilledIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
)

@Composable
fun FilledIconToggleButtonWithMaterialIcons(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    uncheckedIcon: Icon,
    checkedIcon: Icon
) =
    FilledIconToggleButton(
        checked,
        onCheckedChange,
        modifier,
        enabled,
        uncheckedIcon.toUncheckedIconContent(),
        checkedIcon.toCheckedIconContent()
    )

/**
 * @param checkedContent the [Modifier] parameter contains the attributes to be set on this icon on JS. You are supposed to pass this [Modifier] to the top-level composable that you invoke inside.
 */
@Composable
expect fun FilledTonalIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
)

@Composable
fun FilledTonalIconToggleButtonWithMaterialIcons(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    uncheckedIcon: Icon,
    checkedIcon: Icon
) =
    FilledTonalIconToggleButton(
        checked,
        onCheckedChange,
        modifier,
        enabled,
        uncheckedIcon.toUncheckedIconContent(),
        checkedIcon.toCheckedIconContent()
    )

/**
 * @param checkedContent the [Modifier] parameter contains the attributes to be set on this icon on JS. You are supposed to pass this [Modifier] to the top-level composable that you invoke inside.
 */
@Composable
expect fun OutlinedIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    uncheckedContent: @Composable () -> Unit,
    checkedContent: @Composable (Modifier) -> Unit
)

@Composable
fun OutlinedIconToggleButtonWithMaterialIcons(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    uncheckedIcon: Icon,
    checkedIcon: Icon
) =
    OutlinedIconToggleButton(
        checked,
        onCheckedChange,
        modifier,
        enabled,
        uncheckedIcon.toUncheckedIconContent(),
        checkedIcon.toCheckedIconContent()
    )
