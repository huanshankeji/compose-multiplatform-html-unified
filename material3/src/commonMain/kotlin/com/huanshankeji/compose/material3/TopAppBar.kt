package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

/*
https://developer.android.com/develop/ui/compose/components/app-bars
https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-top-app-bar.html
 */

/*
When there is only one action button on JS DOM, the `slot` can be set directly to the `md-icon-button` element,
so it would be conventional to make `navigationIcon` of type `@Composable ((Modifier) -> Unit)? = null` to be consistent with other components in this library.
However, we don't do this for these app bar components to make the APIs more similar to the Compose UI ones, and also because visual consistency is fine here with a wrapping `div`.
 */

/**
 * Small top app bar.
 * @see <a href="https://m3.material.io/components/top-app-bar">Material Design top app bar</a>
 * @see androidx.compose.material3.TopAppBar
 */
@Composable
expect fun TopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    // TODO also try aligning with `scrollBehavior` in Compose UI
    stickyJsDom: Boolean = false,
)

/**
 * Center-aligned top app bar.
 * @see <a href="https://m3.material.io/components/top-app-bar">Material Design top app bar</a>
 * @see androidx.compose.material3.CenterAlignedTopAppBar
 */
@Composable
expect fun CenterAlignedTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    stickyJsDom: Boolean = false,
)

/**
 * Medium top app bar.
 * @see <a href="https://m3.material.io/components/top-app-bar">Material Design top app bar</a>
 * @see androidx.compose.material3.MediumTopAppBar
 */
@Composable
expect fun MediumTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    stickyJsDom: Boolean = false,
)

/**
 * Large top app bar.
 * @see <a href="https://m3.material.io/components/top-app-bar">Material Design top app bar</a>
 * @see androidx.compose.material3.LargeTopAppBar
 */
@Composable
expect fun LargeTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    stickyJsDom: Boolean = false,
)
