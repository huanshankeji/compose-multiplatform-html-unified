package com.huanshankeji.compose.material3.ext

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.text.KeyboardActions
import com.huanshankeji.compose.foundation.text.KeyboardOptions
import com.huanshankeji.compose.foundation.text.toPlatformValue
import com.huanshankeji.compose.ui.Modifier

// This function can be moved into a common file.
fun String?.ToNullableTextComposable(): @Composable (() -> Unit)? =
    this?.let { { Text(it) } }

private fun (@Composable ((Modifier) -> Unit)?).toContentWithoutModifier(): @Composable (() -> Unit)? =
    this?.let { { it(Modifier) } }


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
        label = label.ToNullableTextComposable(),
        placeholder = placeholder.ToNullableTextComposable(),
        leadingIcon = leadingIcon.toContentWithoutModifier(),
        trailingIcon = trailingIcon.toContentWithoutModifier(),
        prefix = prefix.ToNullableTextComposable(),
        suffix = suffix.ToNullableTextComposable(),
        supportingText = supportingText.ToNullableTextComposable(),
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
        label = label.ToNullableTextComposable(),
        placeholder = placeholder.ToNullableTextComposable(),
        leadingIcon = leadingIcon.toContentWithoutModifier(),
        trailingIcon = trailingIcon.toContentWithoutModifier(),
        prefix = prefix.ToNullableTextComposable(),
        suffix = suffix.ToNullableTextComposable(),
        supportingText = supportingText.ToNullableTextComposable(),
        isError = isError,
        keyboardOptions = keyboardOptions.toPlatformValue(),
        keyboardActions = keyboardActions.toPlatformValue(),
        singleLine = singleLine,
        maxLines = lines,
        minLines = lines
    )
