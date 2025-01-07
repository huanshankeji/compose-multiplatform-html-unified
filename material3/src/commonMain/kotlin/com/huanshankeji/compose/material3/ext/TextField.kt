package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.text.KeyboardActions
import com.huanshankeji.compose.foundation.text.KeyboardOptions
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

/**
 * @param leadingIcon the [Modifier] parameter contains the attributes to be set on this icon on JS. You are supposed to pass this [Modifier] to the top-level composable that you invoke inside.
 * @param trailingIcon ditto.
 */
@Composable
expect fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: @Composable ((Modifier) -> Unit)? = null,
    trailingIcon: @Composable ((Modifier) -> Unit)? = null,
    prefix: String? = null,
    suffix: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    lines: Int = 1
    /*
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1
    */
    // pattern: String? // This can be supported with Kotlin's `Regex` so it's not supported here.
)

@Composable
fun TextFieldWithMaterialIcons(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: Icon? = null,
    trailingIcon: Icon? = null,
    prefix: String? = null,
    suffix: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    lines: Int = 1
) =
    TextField(
        value,
        onValueChange,
        modifier,
        enabled,
        readOnly,
        label,
        placeholder,
        leadingIcon.toNullableContentWithModifier(),
        trailingIcon.toNullableContentWithModifier(),
        prefix,
        suffix,
        supportingText,
        isError,
        keyboardOptions,
        keyboardActions,
        singleLine,
        lines
    )


/**
 * @param leadingIcon the [Modifier] parameter contains the attributes to be set on this icon on JS. You are supposed to pass this [Modifier] to the top-level composable that you invoke inside.
 * @param trailingIcon ditto.
 */
@Composable
expect fun OutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: @Composable ((Modifier) -> Unit)? = null,
    trailingIcon: @Composable ((Modifier) -> Unit)? = null,
    prefix: String? = null,
    suffix: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    lines: Int = 1
)

@Composable
fun OutlinedTextFieldWithMaterialIcons(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: Icon? = null,
    trailingIcon: Icon? = null,
    prefix: String? = null,
    suffix: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    lines: Int = 1
) =
    OutlinedTextField(
        value,
        onValueChange,
        modifier,
        enabled,
        readOnly,
        label,
        placeholder,
        leadingIcon.toNullableContentWithModifier(),
        trailingIcon.toNullableContentWithModifier(),
        prefix,
        suffix,
        supportingText,
        isError,
        keyboardOptions,
        keyboardActions,
        singleLine,
        lines
    )
