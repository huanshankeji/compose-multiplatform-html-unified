package com.huanshankeji.compose.material3.labs

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.platform.platformModifier

@Composable
actual fun NavigationDrawer(
    opened: Boolean,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    // TODO: Implement using Material 3 PermanentNavigationDrawer or similar
    // For now, just render content if opened
    if (opened) {
        content()
    }
}

@Composable
actual fun ModalNavigationDrawer(
    opened: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.ModalNavigationDrawer(
        drawerContent = { content() },
        modifier = modifier.platformModifier,
        drawerState = androidx.compose.material3.rememberDrawerState(
            if (opened) androidx.compose.material3.DrawerValue.Open 
            else androidx.compose.material3.DrawerValue.Closed
        ),
        gesturesEnabled = true
    ) {
        // Empty content for the drawer
    }
