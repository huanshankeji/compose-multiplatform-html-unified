package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

// Note: Material 3 uses ExposedDropdownMenuBox instead of Select
// These are simplified placeholder implementations
@Composable
actual fun FilledSelect(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    options: @Composable () -> Unit
) {
    // TODO: Implement using ExposedDropdownMenuBox with TextField
    // For now, this is a placeholder
}

@Composable
actual fun OutlinedSelect(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    label: String?,
    options: @Composable () -> Unit
) {
    // TODO: Implement using ExposedDropdownMenuBox with OutlinedTextField
    // For now, this is a placeholder
}

@Composable
actual fun SelectOption(
    value: String,
    text: String,
    modifier: Modifier
) {
    // TODO: Implement as DropdownMenuItem
    // For now, this is a placeholder
}
