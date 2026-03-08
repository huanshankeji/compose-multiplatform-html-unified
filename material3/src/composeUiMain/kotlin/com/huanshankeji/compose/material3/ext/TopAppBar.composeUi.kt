package com.huanshankeji.compose.material3.ext

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun TopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable () -> Unit,
) =
    androidx.compose.material3.TopAppBar(
        title,
        modifier.platformModifier,
        navigationIcon ?: {},
        { actions() },
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun CenterAlignedTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable () -> Unit,
) =
    androidx.compose.material3.CenterAlignedTopAppBar(
        title,
        modifier.platformModifier,
        navigationIcon ?: {},
        { actions() },
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun MediumTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable () -> Unit,
) =
    androidx.compose.material3.MediumTopAppBar(
        title,
        modifier.platformModifier,
        navigationIcon ?: {},
        { actions() },
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun LargeTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier,
    navigationIcon: @Composable (() -> Unit)?,
    actions: @Composable () -> Unit,
) =
    androidx.compose.material3.LargeTopAppBar(
        title,
        modifier.platformModifier,
        navigationIcon ?: {},
        { actions() },
    )
