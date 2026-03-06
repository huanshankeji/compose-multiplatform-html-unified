package com.huanshankeji.compose.material3.ext

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.foundation.layout.toCommonValue
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.material3.Icon
import com.huanshankeji.compose.material3.IconButton
import com.huanshankeji.compose.ui.Modifier
import androidx.compose.material3.FabPosition as PlatformFabPosition

actual class NavigationIconScope private constructor() {
    @Composable
    actual fun NavButton(onClick: () -> Unit, modifier: Modifier, content: @Composable () -> Unit) =
        IconButton(onClick, modifier, content = content)

    @Composable
    actual fun MaterialIconNavButton(
        onClick: () -> Unit, modifier: Modifier, icon: Icon, contentDescription: String?
    ) =
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
        FabPosition.Center -> PlatformFabPosition.Center
        FabPosition.End -> PlatformFabPosition.End
        FabPosition.EndOverlay -> PlatformFabPosition.EndOverlay
    }

@Composable
private fun topBar(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier,
    topAppBarType: TopAppBarType,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)?,
    actions: @Composable TopAppBarActionsScope.() -> Unit
): @Composable () -> Unit = {
    val navigationIconComposable: @Composable () -> Unit =
        navigationIcon?.let { { NavigationIconScope.instance.it() } } ?: {}
    val actionsComposable: @Composable RowScope.() -> Unit =
        { TopAppBarActionsScope(this).actions() }
    when (topAppBarType) {
        TopAppBarType.Small ->
            TopAppBar(title, topAppBarModifier.platformModifier, navigationIconComposable, actionsComposable)

        TopAppBarType.CenterAligned ->
            CenterAlignedTopAppBar(
                title, topAppBarModifier.platformModifier, navigationIconComposable, actionsComposable
            )

        TopAppBarType.Medium ->
            MediumTopAppBar(title, topAppBarModifier.platformModifier, navigationIconComposable, actionsComposable)

        TopAppBarType.Large ->
            LargeTopAppBar(title, topAppBarModifier.platformModifier, navigationIconComposable, actionsComposable)
    }
}

@Composable
actual fun PrimitiveTopAppBarScaffold(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier,
    topAppBarType: TopAppBarType,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)?,
    actions: @Composable TopAppBarActionsScope.() -> Unit,
    contentModifier: Modifier,
    content: @Composable () -> Unit
) =
    TopAppBarScaffold(title, topAppBarModifier, topAppBarType, navigationIcon, actions) {
        Box(contentModifier.platformModifier) { content() }
    }

@Composable
actual fun TopAppBarScaffold(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier,
    topAppBarType: TopAppBarType,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)?,
    actions: @Composable TopAppBarActionsScope.() -> Unit,
    bottomBar: @Composable (() -> Unit)?,
    snackbarHost: @Composable (() -> Unit)?,
    floatingActionButton: @Composable (() -> Unit)?,
    floatingActionButtonPosition: FabPosition,
    content: @Composable (PaddingValues) -> Unit
) =
    Scaffold(
        topBar = topBar(title, topAppBarModifier, topAppBarType, navigationIcon, actions),
        bottomBar = bottomBar ?: {},
        snackbarHost = snackbarHost ?: {},
        floatingActionButton = floatingActionButton ?: {},
        floatingActionButtonPosition = floatingActionButtonPosition.toPlatformValue()
    ) { content(it.toCommonValue()) }
