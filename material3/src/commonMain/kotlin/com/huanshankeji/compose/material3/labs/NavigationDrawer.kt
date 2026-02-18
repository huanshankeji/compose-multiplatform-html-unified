package com.huanshankeji.compose.material3.labs

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design navigation drawer (labs component).
 *
 * Note: This component uses experimental Material Web labs APIs on the JS platform.
 */
@Composable
expect fun NavigationDrawer(
    opened: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)

/**
 * Material Design modal navigation drawer (labs component).
 *
 * Note: This component uses experimental Material Web labs APIs on the JS platform.
 */
@Composable
expect fun ModalNavigationDrawer(
    opened: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)
