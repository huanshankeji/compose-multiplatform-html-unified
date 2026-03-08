@file:Suppress("DEPRECATION")

package com.huanshankeji.compose.material3

import androidx.compose.runtime.*
import com.huanshankeji.compose.foundation.layout.ColumnScope
import com.huanshankeji.compose.html.material3.MaterialWebLabsApi
import com.huanshankeji.compose.html.material3.MdNavigationDrawerModal
import com.huanshankeji.compose.ui.Modifier
import com.huanshankeji.compose.ui.toAttrs
import com.huanshankeji.compose.web.attributes.isTrueOrNull
import org.jetbrains.compose.web.dom.Div

@Stable
actual class DrawerState actual constructor(initialValue: DrawerValue) {
    private var _currentValue by mutableStateOf(initialValue)

    actual val currentValue: DrawerValue
        get() = _currentValue

    actual val isOpen: Boolean
        get() = _currentValue == DrawerValue.Open

    actual val isClosed: Boolean
        get() = _currentValue == DrawerValue.Closed

    actual suspend fun open() {
        _currentValue = DrawerValue.Open
    }

    actual suspend fun close() {
        _currentValue = DrawerValue.Closed
    }
}

@Composable
actual fun rememberDrawerState(initialValue: DrawerValue): DrawerState =
    remember { DrawerState(initialValue) }

@MaterialWebLabsApi
@Composable
actual fun ModalNavigationDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier,
    drawerState: DrawerState,
    content: @Composable () -> Unit,
) =
    Div {
        MdNavigationDrawerModal(
            opened = drawerState.isOpen.isTrueOrNull(),
            attrs = modifier.toAttrs(),
        ) {
            drawerContent()
        }
        content()
    }

@Composable
actual fun ModalDrawerSheet(
    modifier: Modifier,
    content: @Composable (ColumnScope.() -> Unit),
) =
    Card(modifier, content = content)
