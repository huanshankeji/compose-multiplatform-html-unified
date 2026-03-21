package com.huanshankeji.compose.material3

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun TopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable (() -> Unit)?,
    stickyJsDom: Boolean,
) =
    TopAppBar(
        title,
        modifier.platformModifier,
        navigationIcon ?: {},
        actions.toRowScopeContent(),
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun CenterAlignedTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable (() -> Unit)?,
    stickyJsDom: Boolean,
) =
    CenterAlignedTopAppBar(
        title,
        modifier.platformModifier,
        navigationIcon ?: {},
        actions.toRowScopeContent(),
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun MediumTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable (() -> Unit)?,
    stickyJsDom: Boolean,
) =
    MediumTopAppBar(
        title,
        modifier.platformModifier,
        navigationIcon ?: {},
        actions.toRowScopeContent(),
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun LargeTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable (() -> Unit)?,
    stickyJsDom: Boolean,
) =
    LargeTopAppBar(
        title,
        modifier.platformModifier,
        navigationIcon ?: {},
        actions.toRowScopeContent(),
    )

@Composable
private fun (@Composable () -> Unit)?.toRowScopeContent(): @Composable (RowScope.() -> Unit) =
    this?.let { { it() } } ?: {}
