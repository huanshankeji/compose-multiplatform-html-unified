package com.huanshankeji.compose.material2.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

expect class NavigationIconScope {
    @Composable
    fun NavButton(onClick: () -> Unit, modifier: Modifier = Modifier, content: @Composable () -> Unit)

    @Composable
    fun MaterialIconNavButton(
        onClick: () -> Unit, modifier: Modifier = Modifier, icon: Icon, contentDescription: String?
    )
}

expect class TopAppBarActionsScope {
    @Composable
    fun ActionButton(onClick: () -> Unit, modifier: Modifier = Modifier, content: @Composable () -> Unit)

    @Composable
    fun MaterialIconActionButton(
        onClick: () -> Unit, modifier: Modifier = Modifier, icon: Icon, contentDescription: String?
    )
}

// copied and adapted from `androidx.compose.material.FabPosition`
enum class FabPosition {
    Start, Center, End
}

/**
 * This one doesn't fill parent height on JS.
 * @param contentModifier be cautious when passing the parameter, as the content CSS class `mdc-top-app-bar--fixed-adjust` has its styles which may be overridden.
 */
@Composable
expect fun PrimitiveTopAppBarScaffold(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier = Modifier,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)? = null,
    actions: @Composable TopAppBarActionsScope.() -> Unit = {},
    contentModifier: Modifier = Modifier,
    content: @Composable () -> Unit
)

/**
 * This variant fills parent space automatically and internally uses a flexbox on JS.
 * For it to work properly on JS DOM, it's recommended to use it as the top level element and to set these CSS styles on body:
 * ```
 * body {
 *     margin: 0;
 *     height: 100vh;
 * }
 * ```
 *
 * @param snackbarHost `androidx.compose.material.Scaffold` has a `SnackbarHostState` parameter for this lambda, but `androidx.compose.material3.Scaffold` doesn't. Therefore, we think this is probably a design flaw and don't provide the parameter.
 * @param isFloatingActionButtonDockedComposeUi available on `androidx.compose` targets only.
// * @param isContentOverflowingOrExpandingJs available on JS DOM only.
 */
@Composable
expect fun TopAppBarScaffold(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier = Modifier,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)? = null,
    actions: @Composable TopAppBarActionsScope.() -> Unit = {},
    bottomBar: @Composable (() -> Unit)? = null,
    snackbarHost: @Composable (() -> Unit)? = null,
    floatingActionButton: @Composable (() -> Unit)? = null,
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDockedComposeUi: Boolean = false,
    //isContentOverflowingOrExpandingJsDom: Boolean = true, // always overflows
    content: @Composable (PaddingValues) -> Unit
)
