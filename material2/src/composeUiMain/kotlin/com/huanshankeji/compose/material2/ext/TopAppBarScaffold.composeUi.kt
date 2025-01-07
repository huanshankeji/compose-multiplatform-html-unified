package com.huanshankeji.compose.material2.ext

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.foundation.layout.toCommonValue
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material2.Icon
import com.huanshankeji.compose.material2.IconButton
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.material.FabPosition.Companion as PlatformFabPosition

actual class NavigationIconScope private constructor() {
    @Composable
    actual fun NavButton(onClick: () -> Unit, modifier: Modifier, content: @Composable () -> Unit) =
        IconButton(onClick, modifier, content = content)

    @Composable
    actual fun MaterialIconNavButton(onClick: () -> Unit, modifier: Modifier, icon: Icon, contentDescription: String?) =
        IconButton(onClick, modifier) { Icon(icon, contentDescription) }

    companion object {
        val instance = NavigationIconScope()
    }
}

actual class TopAppBarActionsScope(val rowScope: RowScope) {
    @Composable
    actual fun ActionButton(onClick: () -> Unit, modifier: Modifier, content: @Composable () -> Unit) =
        IconButton(onClick, modifier, content = content)

    @Composable
    actual fun MaterialIconActionButton(
        onClick: () -> Unit, modifier: Modifier, icon: Icon, contentDescription: String?
    ) =
        IconButton(onClick, modifier) { Icon(icon, contentDescription) }
}

fun FabPosition.toPlatformValue() =
    when (this) {
        FabPosition.Start -> PlatformFabPosition.Start
        FabPosition.Center -> PlatformFabPosition.Center
        FabPosition.End -> PlatformFabPosition.End
    }

@Composable
actual fun PrimitiveTopAppBarScaffold(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)?,
    actions: @Composable TopAppBarActionsScope.() -> Unit,
    contentModifier: Modifier,
    content: @Composable () -> Unit
) =
    TopAppBarScaffold(title, topAppBarModifier, navigationIcon, actions) {
        Box(contentModifier.platformModifier) { content() }
    }

@Composable
actual fun TopAppBarScaffold(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)?,
    actions: @Composable TopAppBarActionsScope.() -> Unit,
    bottomBar: @Composable (() -> Unit)?,
    snackbarHost: @Composable (() -> Unit)?,
    floatingActionButton: @Composable (() -> Unit)?,
    floatingActionButtonPosition: FabPosition,
    isFloatingActionButtonDockedComposeUi: Boolean,
    content: @Composable (PaddingValues) -> Unit
) =
    Scaffold(
        topBar = {
            TopAppBar(
                title,
                topAppBarModifier.platformModifier,
                navigationIcon?.let { { NavigationIconScope.instance.it() } },
                { TopAppBarActionsScope(this).actions() })
        },
        bottomBar = bottomBar ?: {},
        snackbarHost = snackbarHost?.let { { snackbarHost() } }
            ?: { androidx.compose.material.SnackbarHost(it) },
        floatingActionButton = floatingActionButton ?: {},
        floatingActionButtonPosition = floatingActionButtonPosition.toPlatformValue(),
        isFloatingActionButtonDocked = isFloatingActionButtonDockedComposeUi
    ) { content(it.toCommonValue()) }
