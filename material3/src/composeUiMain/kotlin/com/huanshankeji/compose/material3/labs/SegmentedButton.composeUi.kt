package com.huanshankeji.compose.material3.labs

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

// Note: Material 3 for Compose UI doesn't have SegmentedButton yet
// This is a placeholder that compiles but doesn't render
@Composable
actual fun SegmentedButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: @Composable (() -> Unit)?,
    label: @Composable () -> Unit
) {
    // TODO: Implement using Material 3 components when available
    // For now, this is a no-op placeholder
}

@Composable
actual fun SegmentedButtonSet(
    modifier: Modifier,
    multiselect: Boolean,
    content: @Composable () -> Unit
) {
    // TODO: Implement using Material 3 components when available
    // For now, just render the content directly
    content()
}
