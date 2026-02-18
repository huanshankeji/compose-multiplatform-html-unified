package com.huanshankeji.compose.material3.labs

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design list item (labs component).
 *
 * Note: This component uses experimental Material Web labs APIs on the JS platform.
 */
@Composable
expect fun ListItem(
    modifier: Modifier = Modifier,
    multiline: Boolean = false,
    content: @Composable () -> Unit
)
