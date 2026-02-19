package com.huanshankeji.compose.material3

import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun TabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    tabs: @Composable () -> Unit
) =
    androidx.compose.material3.TabRow(
        selectedTabIndex,
        modifier.platformModifier,
        tabs = tabs
    )

@Composable
actual fun Tab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    text: @Composable (() -> Unit)?,
    icon: @Composable (() -> Unit)?
) =
    androidx.compose.material3.Tab(
        selected,
        onClick,
        modifier.platformModifier,
        enabled,
        text,
        icon
    )
