package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/**
 * Material Design navigation drawer.
 *
 * Note: This component uses experimental Material Web labs APIs on the JS platform.
 *
 * @see <a href="https://m3.material.io/components/navigation-drawer/overview">Material Design navigation drawer</a>
 */
@Composable
expect fun NavigationDrawer(
    opened: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)

/**
 * Material Design modal navigation drawer.
 *
 * Delegates to `androidx.compose.material3.ModalNavigationDrawer` on Compose UI platforms.
 * Note: Uses experimental Material Web labs APIs on the JS platform.
 *
 * @see <a href="https://m3.material.io/components/navigation-drawer/overview">Material Design navigation drawer</a>
 */
@Composable
expect fun ModalNavigationDrawer(
    opened: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)
