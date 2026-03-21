@file:Suppress("DEPRECATION")

package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.foundation.layout.ColumnScope
import com.huanshankeji.compose.ui.Modifier

/*
https://developer.android.com/develop/ui/compose/components/drawer
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-permanent-navigation-drawer.html
 */

private const val NAVIGATION_DRAWER_DEPRECATION_MESSAGE =
    "Navigation Drawer APIs are deprecated due to the labs `MdNavigationDrawerModal` component being immature " +
            "and hard to configure to provide consistent visual results. " +
            "We may add support for new components such as the Material 3 Expressive navigation rail that will replace these instead in the future."

@Deprecated(NAVIGATION_DRAWER_DEPRECATION_MESSAGE)
enum class DrawerValue {
    Closed, Open
}

@Deprecated(NAVIGATION_DRAWER_DEPRECATION_MESSAGE)
@Stable
expect /*value*/ class DrawerState(initialValue: DrawerValue) {
    val currentValue: DrawerValue
    val isOpen: Boolean
    val isClosed: Boolean
    suspend fun open()
    suspend fun close()
}

@Deprecated(NAVIGATION_DRAWER_DEPRECATION_MESSAGE)
@Composable
expect fun rememberDrawerState(initialValue: DrawerValue): DrawerState

/**
 * Material Design modal navigation drawer.
 *
 * Delegates to `androidx.compose.material3.ModalNavigationDrawer` on Compose UI platforms.
 * Note: Uses experimental Material Web labs APIs on the JS platform.
 *
 * @see <a href="https://m3.material.io/components/navigation-drawer/overview">Material Design navigation drawer</a>
 */
@Deprecated(NAVIGATION_DRAWER_DEPRECATION_MESSAGE)
@Composable
expect fun ModalNavigationDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    content: @Composable () -> Unit,
)

// DismissibleNavigationDrawer
// PermanentNavigationDrawer

@Deprecated(NAVIGATION_DRAWER_DEPRECATION_MESSAGE)
@Composable
expect fun ModalDrawerSheet(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
)

// NavigationDrawerItem // no corresponding Material Web implementation
