package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.*
import com.huanshankeji.compose.material3.Text
import com.huanshankeji.compose.ui.Modifier

private data class SelectContext(val onSelect: (String) -> Unit)

private val LocalSelectContext = compositionLocalOf<SelectContext?> { null }

@Composable
actual fun FilledSelect(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    options: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBoxWithTextField(
        expanded = expanded,
        onExpandedChange = { if (enabled) expanded = it },
        modifier = modifier,
        textFieldArgs = ExposedDropdownMenuBoxTextFieldArgs(
            value = value,
            readOnly = true,
            singleLine = true,
            label = label ?: ""
        ),
        exposedDropdownMenuArgs = ExposedDropdownMenuArgs(
            expanded = expanded,
            onDismissRequestComposeUi = { expanded = false },
            onCloseJsDom = { expanded = false }
        ) {
            CompositionLocalProvider(LocalSelectContext provides SelectContext { selectedValue ->
                onValueChange(selectedValue)
                expanded = false
            }) {
                options()
            }
        }
    )
}

@Composable
actual fun OutlinedSelect(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    options: @Composable () -> Unit
) =
    // TODO use `ExposedDropdownMenuBoxWithOutlinedTextField`
    FilledSelect(value, onValueChange, modifier, enabled, label, options)

@Composable
actual fun SelectOption(
    value: String,
    text: String,
    modifier: Modifier
) {
    val context = LocalSelectContext.current
    DropdownMenuItem(
        text = { Text(text) },
        onClick = { context?.onSelect?.invoke(value) },
        modifier = modifier
    )
}
