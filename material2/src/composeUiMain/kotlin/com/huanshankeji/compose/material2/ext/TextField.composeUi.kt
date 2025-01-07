package com.huanshankeji.compose.material2.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.text.KeyboardActions
import com.huanshankeji.compose.foundation.text.KeyboardOptions
import com.huanshankeji.compose.foundation.text.toPlatformValue
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material2.Icon
import com.huanshankeji.compose.material2.Text
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    singleLine: Boolean
) =
    androidx.compose.material.TextField(
        value,
        onValueChange,
        modifier.platformModifier,
        enabled = enabled,
        label = label?.let { { Text(it) } },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions.toPlatformValue(),
        keyboardActions = keyboardActions.toPlatformValue(),
        singleLine = singleLine
    )

@Composable
actual fun TextFieldWithMaterialIcons(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    leadingIcon: Icon?,
    trailingIcon: Icon?,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    singleLine: Boolean
) =
    TextField(
        value,
        onValueChange,
        modifier,
        enabled,
        label,
        leadingIcon?.let { { Icon(it, null) } },
        trailingIcon?.let { { Icon(it, null) } },
        keyboardOptions, keyboardActions, singleLine
    )


@Composable
actual fun OutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    singleLine: Boolean
) =
    androidx.compose.material.OutlinedTextField(
        value,
        onValueChange,
        modifier.platformModifier,
        enabled = enabled,
        label = label?.let { { Text(it) } },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions.toPlatformValue(),
        keyboardActions = keyboardActions.toPlatformValue(),
        singleLine = singleLine
    )

@Composable
actual fun OutlinedTextFieldWithMaterialIcons(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    leadingIcon: Icon?,
    trailingIcon: Icon?,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    singleLine: Boolean
) =
    OutlinedTextField(
        value,
        onValueChange,
        modifier,
        enabled,
        label,
        leadingIcon?.let { { Icon(it, null) } },
        trailingIcon?.let { { Icon(it, null) } },
        keyboardOptions, keyboardActions, singleLine
    )


@Composable
actual fun TextArea(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    lines: Int
) =
    androidx.compose.material.TextField(
        value,
        onValueChange,
        modifier.platformModifier,
        enabled = enabled,
        label = label?.let { { Text(it) } },
        keyboardOptions = keyboardOptions.toPlatformValue(),
        keyboardActions = keyboardActions.toPlatformValue(),
        maxLines = lines,
        minLines = lines
    )
