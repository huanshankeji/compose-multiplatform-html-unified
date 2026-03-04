package com.huanshankeji.compose.material3

import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.huanshankeji.compose.foundation.layout.ColumnScope
import com.huanshankeji.compose.foundation.layout.toCommonColumnScopeContent
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.material3.DrawerState as PlatformDrawerState
import androidx.compose.material3.DrawerValue as PlatformDrawerValue

fun DrawerValue.toPlatformValue(): PlatformDrawerValue =
    when (this) {
        DrawerValue.Closed -> PlatformDrawerValue.Closed
        DrawerValue.Open -> PlatformDrawerValue.Open
    }

fun PlatformDrawerValue.toCommonValue(): DrawerValue =
    when (this) {
        PlatformDrawerValue.Closed -> DrawerValue.Closed
        PlatformDrawerValue.Open -> DrawerValue.Open
    }

@Stable
actual class DrawerState(val platformValue: PlatformDrawerState) {
    actual constructor(initialValue: DrawerValue) : this(PlatformDrawerState(initialValue.toPlatformValue()))

    actual val currentValue: DrawerValue
        get() = platformValue.currentValue.toCommonValue()

    actual val isOpen: Boolean
        get() = platformValue.isOpen

    actual val isClosed: Boolean
        get() = platformValue.isClosed

    actual suspend fun open() = platformValue.open()
    actual suspend fun close() = platformValue.close()
}

fun PlatformDrawerState.toCommonValue() =
    DrawerState(this)

@Composable
actual fun rememberDrawerState(initialValue: DrawerValue): DrawerState =
    androidx.compose.material3.rememberDrawerState(initialValue.toPlatformValue()).toCommonValue()

@Composable
actual fun ModalNavigationDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) =
    androidx.compose.material3.ModalNavigationDrawer(
        drawerContent,
        modifier.platformModifier,
        drawerState.platformValue,
        content = content
    )

@Composable
actual fun ModalDrawerSheet(
    modifier: Modifier,
    content: @Composable ColumnScope.() -> Unit,
) =
    ModalDrawerSheet(modifier.platformModifier, content = content.toCommonColumnScopeContent())
