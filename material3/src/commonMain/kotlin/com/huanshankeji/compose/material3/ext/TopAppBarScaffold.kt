package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.foundation.layout.PaddingValues
import com.huanshankeji.compose.material.icons.Icon
import com.huanshankeji.compose.ui.Modifier

// copied and adapted from `TopAppBarScaffold.kt` in material2

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

// copied and adapted from `androidx.compose.material3.FabPosition`
enum class FabPosition {
    Center, End, EndOverlay
}

/** The type of the top app bar, determining the variant to use. */
enum class TopAppBarType {
    /** A small top app bar. @see <a href="https://m3.material.io/components/top-app-bar/overview">Material Design top app bars</a> */
    Small,

    /** A center-aligned top app bar. */
    CenterAligned,

    /** A medium top app bar. */
    Medium,

    /** A large top app bar. */
    Large
}

/**
 * This one doesn't fill parent height on JS.
 * @param contentModifier be cautious when passing the parameter, as the content may have styles applied.
 */
@Composable
expect fun PrimitiveTopAppBarScaffold(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier = Modifier,
    topAppBarType: TopAppBarType = TopAppBarType.Small,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)? = null,
    actions: @Composable TopAppBarActionsScope.() -> Unit = {},
    contentModifier: Modifier = Modifier,
    content: @Composable () -> Unit
)

/**
 * Material Design top app bar scaffold.
 *
 * This variant fills parent space automatically and internally uses a flexbox on JS.
 * For it to work properly on JS DOM, it's recommended to use it as the top level element and to set these CSS styles on body:
 * ```
 * body {
 *     margin: 0;
 *     height: 100vh;
 * }
 * ```
 *
 * @param snackbarHost `androidx.compose.material3.Scaffold` doesn't have a `SnackbarHostState` parameter for the snackbar lambda.
 * We recommend passing a [com.huanshankeji.compose.material3.SnackbarHost] wrapped in a lambda here.
 *
 * @see <a href="https://m3.material.io/components/top-app-bar/overview">Material Design top app bars</a>
 * @see androidx.compose.material3.TopAppBar
 * @see androidx.compose.material3.Scaffold
 */
@Composable
expect fun TopAppBarScaffold(
    title: @Composable () -> Unit,
    topAppBarModifier: Modifier = Modifier,
    topAppBarType: TopAppBarType = TopAppBarType.Small,
    navigationIcon: @Composable (NavigationIconScope.() -> Unit)? = null,
    actions: @Composable TopAppBarActionsScope.() -> Unit = {},
    bottomBar: @Composable (() -> Unit)? = null,
    snackbarHost: @Composable (() -> Unit)? = null,
    floatingActionButton: @Composable (() -> Unit)? = null,
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable (PaddingValues) -> Unit
)
