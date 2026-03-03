package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.text.KeyboardActions
import com.huanshankeji.compose.foundation.text.KeyboardOptions
import com.huanshankeji.compose.foundation.text.toPlatformValue
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ext.toNullableContentWithoutModifier

@Composable
actual fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    readOnly: Boolean,
    label: String?,
    placeholder: String?,
    leadingIcon: @Composable ((Modifier) -> Unit)?,
    trailingIcon: @Composable ((Modifier) -> Unit)?,
    prefix: String?,
    suffix: String?,
    supportingText: String?,
    isError: Boolean,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    singleLine: Boolean,
    lines: Int
) =
    androidx.compose.material3.TextField(
        value,
        onValueChange,
        modifier.platformModifier,
        enabled,
        readOnly,
        label = label.toNullableTextComposable(),
        placeholder = placeholder.toNullableTextComposable(),
        leadingIcon = leadingIcon.toNullableContentWithoutModifier(),
        trailingIcon = trailingIcon.toNullableContentWithoutModifier(),
        prefix = prefix.toNullableTextComposable(),
        suffix = suffix.toNullableTextComposable(),
        supportingText = supportingText.toNullableTextComposable(),
        isError = isError,
        keyboardOptions = keyboardOptions.toPlatformValue(),
        keyboardActions = keyboardActions.toPlatformValue(),
        singleLine = singleLine,
        maxLines = lines,
        minLines = lines
    )


@Composable
actual fun OutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    readOnly: Boolean,
    label: String?,
    placeholder: String?,
    leadingIcon: @Composable ((Modifier) -> Unit)?,
    trailingIcon: @Composable ((Modifier) -> Unit)?,
    prefix: String?,
    suffix: String?,
    supportingText: String?,
    isError: Boolean,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    singleLine: Boolean,
    lines: Int
) =
    androidx.compose.material3.OutlinedTextField(
        value,
        onValueChange,
        modifier.platformModifier,
        enabled,
        readOnly,
        label = label.toNullableTextComposable(),
        placeholder = placeholder.toNullableTextComposable(),
        leadingIcon = leadingIcon.toNullableContentWithoutModifier(),
        trailingIcon = trailingIcon.toNullableContentWithoutModifier(),
        prefix = prefix.toNullableTextComposable(),
        suffix = suffix.toNullableTextComposable(),
        supportingText = supportingText.toNullableTextComposable(),
        isError = isError,
        keyboardOptions = keyboardOptions.toPlatformValue(),
        keyboardActions = keyboardActions.toPlatformValue(),
        singleLine = singleLine,
        maxLines = lines,
        minLines = lines
    )
