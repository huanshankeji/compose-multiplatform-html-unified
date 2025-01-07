package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.huanshankeji.compose.foundation.text.KeyboardActions
import com.huanshankeji.compose.foundation.text.KeyboardOptions
import com.huanshankeji.compose.foundation.text.attrsFrom
import com.huanshankeji.compose.html.material3.MdFilledTextField
import com.huanshankeji.compose.html.material3.MdOutlinedTextField
import com.huanshankeji.compose.html.material3.MdTextFieldScope
import com.huanshankeji.compose.html.material3.TextareaInputType
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.ui.toCommonModifier
import com.huanshankeji.compose.web.attributes.ext.onInput
import com.huanshankeji.compose.web.attributes.isFalseOrNull
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import com.huanshankeji.compose.web.dom.ext.value
import com.varabyte.kobweb.compose.ui.attrsModifier
import org.jetbrains.compose.web.attributes.AttrsScope
import org.w3c.dom.HTMLElement

private fun inputType(singleLine: Boolean, lines: Int) =
    // This is consistent with the `androidx.compose.material3` text field behavior.
    if (singleLine || lines == 1) null else TextareaInputType

private fun Modifier.toTextFieldAttrs(
    onValueChange: (String) -> Unit, keyboardOptions: KeyboardOptions, keyboardActions: KeyboardActions,
): AttrsScope<HTMLElement>.() -> Unit =
    toAttrs {
        // see https://stackoverflow.com/questions/574941/best-way-to-track-onchange-as-you-type-in-input-type-text
        onInput { onValueChange(it.target.value) }

        // TODO `keyboardOptions.imeAction` is not working because `enterkeyhint` is not passed to the underlying `input`.
        attrsFrom(keyboardOptions, keyboardActions)
    }

private fun TextFieldContent(
    value: String,
    leadingIcon: @Composable ((Modifier) -> Unit)?,
    trailingIcon: @Composable ((Modifier) -> Unit)?,
): @Composable MdTextFieldScope.() -> Unit = {
    with(elementScope) {
        // This can't be put in `AttrsScope.ref` because it needs to run whenever `value` changes instead of just when the `HTMLElement` is added.
        DisposableEffect(value) {
            scopeElement.value = value
            onDispose {}
        }
    }
    leadingIcon?.invoke(PlatformModifier.attrsModifier { slot(MdTextFieldScope.Slot.LeadingIcon) }.toCommonModifier())
    trailingIcon?.invoke(PlatformModifier.attrsModifier { slot(MdTextFieldScope.Slot.TrailingIcon) }.toCommonModifier())
}


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
    MdFilledTextField(
        enabled.isFalseOrNull(),
        isError.isTrueOrNull(),
        supportingText, // TODO Is passing `supportingText` as `errorText` correct?
        label,
        //value = value, // This causes the caret to be reset to the start whenever the value changes if the `type` attribute is set. And since the value is set in the `content` `DisposableEffect` it seems OK not skip it here.
        prefixText = prefix,
        suffixText = suffix,
        hasLeadingIcon = leadingIcon?.let { true },
        hasTrailingIcon = trailingIcon?.let { true },
        supportingText = supportingText,
        rows = if (singleLine) null else lines,
        placeholder = placeholder,
        readOnly = readOnly.isTrueOrNull(),
        type = inputType(singleLine, lines),

        attrs = modifier.toTextFieldAttrs(onValueChange, keyboardOptions, keyboardActions),
        content = TextFieldContent(value, leadingIcon, trailingIcon)
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
    MdOutlinedTextField(
        enabled.isFalseOrNull(),
        isError.isTrueOrNull(),
        supportingText, // TODO Is passing `supportingText` as `errorText` correct?
        label,
        //value = value, // This causes the caret to be reset to the start whenever the value changes if the `type` attribute is set. And since the value is set in the `content` `DisposableEffect` it seems OK not skip it here.
        prefixText = prefix,
        suffixText = suffix,
        hasLeadingIcon = leadingIcon?.let { true },
        hasTrailingIcon = trailingIcon?.let { true },
        supportingText = supportingText,
        rows = if (singleLine) null else lines,
        placeholder = placeholder,
        readOnly = readOnly.isTrueOrNull(),
        type = inputType(singleLine, lines),

        attrs = modifier.toTextFieldAttrs(onValueChange, keyboardOptions, keyboardActions),
        content = TextFieldContent(value, leadingIcon, trailingIcon)
    )
