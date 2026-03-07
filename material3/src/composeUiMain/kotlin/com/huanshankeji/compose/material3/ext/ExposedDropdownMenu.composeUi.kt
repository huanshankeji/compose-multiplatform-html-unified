package com.huanshankeji.compose.material3.ext

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.PlatformModifier

@Composable
actual fun ExposedDropdownMenuBox(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier,
    content: @Composable ExposedDropdownMenuBoxScope.() -> Unit,
) =
    @OptIn(ExperimentalMaterial3Api::class)
    androidx.compose.material3.ExposedDropdownMenuBox(expanded, onExpandedChange, modifier.platformModifier) {
        ExposedDropdownMenuBoxScope(this).content()
    }

@OptIn(ExperimentalMaterial3Api::class)
actual class ExposedDropdownMenuBoxScope(val platformValue: androidx.compose.material3.ExposedDropdownMenuBoxScope) {
    actual fun Modifier.menuAnchor(): Modifier =
        platformModify { with(platformValue) { menuAnchor() } } // TODO add `type` and `disabled` and try to unify the behavior with JS

    @Composable
    actual fun ExposedDropdownMenu(
        expanded: Boolean,
        onDismissRequestComposeUi: () -> Unit,
        onCloseJsDom: () -> Unit,
        modifier: Modifier,
        matchAnchorWidthComposeUi: Boolean,
        content: @Composable () -> Unit,
    ) =
        platformValue.ExposedDropdownMenu(
            expanded,
            onDismissRequestComposeUi,
            modifier.platformModifier,
            matchAnchorWidth = matchAnchorWidthComposeUi,
        ) { content() }
}


@Composable
actual fun ExposedDropdownMenuBoxScope.ExposedDropdownMenuBoxTextField(
    expanded: Boolean, args: ExposedDropdownMenuBoxTextFieldArgs
) =
    with(args) {
        @OptIn(ExperimentalMaterial3Api::class)
        TextField(
            // TODO add `type`
            modifier = with(platformValue) { PlatformModifier.menuAnchor(/*MenuAnchorType.PrimaryNotEditable*/) },
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            singleLine = singleLine,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            supportingText = supportingText.toNullableTaglessText(),
            isError = isError,
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
    }

@Composable
actual fun ExposedDropdownMenuBoxScope.ExposedDropdownMenuBoxOutlinedTextField(
    expanded: Boolean,
    args: ExposedDropdownMenuBoxTextFieldArgs,
) =
    with(args) {
        @OptIn(ExperimentalMaterial3Api::class)
        OutlinedTextField(
            // TODO add `type`
            modifier = with(platformValue) { PlatformModifier.menuAnchor(/*MenuAnchorType.PrimaryNotEditable*/) },
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            singleLine = singleLine,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            supportingText = supportingText.toNullableTaglessText(),
            isError = isError,
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
    }

//internal expect fun menuAnchorModifier(editable: Boolean)
