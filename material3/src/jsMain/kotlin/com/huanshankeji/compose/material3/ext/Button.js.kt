package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.html.material3.MdButtonScope
import com.huanshankeji.compose.material3.*
import com.huanshankeji.compose.ui.Modifier

private fun (@Composable () -> Unit).toMdButtonScopeContent(
    icon: @Composable ((Modifier) -> Unit)?
): @Composable MdButtonScope.() -> Unit = {
    // see https://github.com/material-components/material-web/blob/main/docs/components/button.md#icon

    this@toMdButtonScopeContent()
    nullableContentWithSlot(icon, MdButtonScope.Slot.Icon)
}


@Composable
actual fun Button(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?,
    isTrailingIcon: Boolean,
    content: @Composable () -> Unit
) =
    CommonButton(onClick, modifier, enabled, isTrailingIcon, content.toMdButtonScopeContent(icon))


@Composable
actual fun ElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?,
    isTrailingIcon: Boolean,
    content: @Composable () -> Unit
) =
    CommonElevatedButton(onClick, modifier, enabled, isTrailingIcon, content.toMdButtonScopeContent(icon))

@Composable
actual fun FilledTonalButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?,
    isTrailingIcon: Boolean,
    content: @Composable () -> Unit
) =
    CommonFilledTonalButton(onClick, modifier, enabled, isTrailingIcon, content.toMdButtonScopeContent(icon))

@Composable
actual fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?,
    isTrailingIcon: Boolean,
    content: @Composable () -> Unit
) =
    CommonOutlinedButton(onClick, modifier, enabled, isTrailingIcon, content.toMdButtonScopeContent(icon))

@Composable
actual fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable ((Modifier) -> Unit)?,
    isTrailingIcon: Boolean,
    content: @Composable () -> Unit
) =
    CommonTextButton(onClick, modifier, enabled, isTrailingIcon, content.toMdButtonScopeContent(icon))
