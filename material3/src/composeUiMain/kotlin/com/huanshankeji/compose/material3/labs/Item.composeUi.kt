package com.huanshankeji.compose.material3.labs

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

// Note: Material 3 for Compose UI doesn't have a direct ListItem equivalent in labs
// This is a placeholder that compiles but doesn't render
@Composable
actual fun ListItem(
    modifier: Modifier,
    multiline: Boolean,
    content: @Composable () -> Unit
) {
    // TODO: Implement using Material 3 ListItem when available
    // For now, just render the content directly
    content()
}
