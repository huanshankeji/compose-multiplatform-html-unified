package com.huanshankeji.compose.material2.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.text.KeyboardActions
import com.huanshankeji.compose.foundation.text.KeyboardOptions
import com.huanshankeji.compose.foundation.text.attrsFrom
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material2.icons.mdcIconWithStyle
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import dev.petuska.kmdc.core.MDCContent
import dev.petuska.kmdc.textfield.MDCTextArea
import dev.petuska.kmdc.textfield.MDCTextField
import dev.petuska.kmdc.textfield.MDCTextFieldScope
import dev.petuska.kmdc.textfield.MDCTextFieldType
import dev.petuska.kmdc.textfield.icon.MDCTextFieldLeadingIcon
import dev.petuska.kmdc.textfield.icon.MDCTextFieldTrailingIcon
import org.jetbrains.compose.web.dom.Text

@Composable
fun CommonTextFieldWithMDCContentIcons(
    value: String,
    type: MDCTextFieldType,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    leadingIcon: MDCContent<MDCTextFieldScope>? = null,
    trailingIcon: MDCContent<MDCTextFieldScope>? = null,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) =
    MDCTextField(
        value,
        type,
        !enabled,
        label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        attrs = modifier.toAttrs {
            onInput { onValueChange(it.value) }

            attrsFrom(keyboardOptions, keyboardActions)
        }
    )

@Composable
fun CommonTextFieldWithIconsLikeComposeUi(
    value: String,
    type: MDCTextFieldType,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) =
    CommonTextFieldWithMDCContentIcons(
        value,
        type,
        onValueChange,
        modifier,
        enabled,
        label,
        leadingIcon?.let { { it() } },
        trailingIcon?.let { { it() } },
        keyboardOptions,
        keyboardActions
    )

@Composable
fun CommonTextFieldWithMaterialIcons(
    value: String,
    type: MDCTextFieldType,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    leadingIcon: Icon?,
    trailingIcon: Icon?,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) =
    CommonTextFieldWithMDCContentIcons(
        value,
        type,
        onValueChange,
        modifier,
        enabled,
        label,
        // see https://github.com/mpetuska/kmdc/blob/master/sandbox/src/jsMain/showcases/MDCTextField.kt
        leadingIcon?.let { { MDCTextFieldLeadingIcon(attrs = { mdcIconWithStyle() }) { Text(it.name) } } },
        trailingIcon?.let { { MDCTextFieldTrailingIcon(attrs = { mdcIconWithStyle() }) { Text(it.name) } } },
        keyboardOptions,
        keyboardActions
    )


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
    CommonTextFieldWithIconsLikeComposeUi(
        value,
        MDCTextFieldType.Filled,
        onValueChange,
        modifier,
        enabled,
        label,
        leadingIcon,
        trailingIcon,
        keyboardOptions,
        keyboardActions
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
    CommonTextFieldWithMaterialIcons(
        value,
        MDCTextFieldType.Filled,
        onValueChange,
        modifier,
        enabled,
        label,
        leadingIcon,
        trailingIcon,
        keyboardOptions,
        keyboardActions
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
    CommonTextFieldWithIconsLikeComposeUi(
        value,
        MDCTextFieldType.Outlined,
        onValueChange,
        modifier,
        enabled,
        label,
        leadingIcon,
        trailingIcon,
        keyboardOptions,
        keyboardActions
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
    CommonTextFieldWithMaterialIcons(
        value,
        MDCTextFieldType.Outlined,
        onValueChange,
        modifier,
        enabled,
        label,
        leadingIcon,
        trailingIcon,
        keyboardOptions,
        keyboardActions
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
    MDCTextArea(
        value,
        disabled = !enabled,
        label = label,
        rows = lines.toUInt(),
        attrs = modifier.toAttrs {
            onInput { onValueChange(it.value) }
        })
