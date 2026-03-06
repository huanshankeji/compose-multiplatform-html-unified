package com.huanshankeji.compose.material3.ext

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

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
    actions: @Composable () -> Unit = {},
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
    actions: @Composable () -> Unit = {},
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
    actions: @Composable () -> Unit = {},
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
    actions: @Composable () -> Unit = {},
)
