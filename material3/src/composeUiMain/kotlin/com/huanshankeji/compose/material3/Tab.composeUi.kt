package com.huanshankeji.compose.material3

import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.runtime.Composable
import com.huanshankeji.compose.ui.Modifier

@Composable
actual fun PrimaryTabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    tabs: @Composable () -> Unit,
) =
    PrimaryTabRow(
        selectedTabIndex,
        modifier.platformModifier,
        tabs = tabs
    )

@Composable
actual fun SecondaryTabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    tabs: @Composable () -> Unit,
) =
    SecondaryTabRow(
        selectedTabIndex,
        modifier.platformModifier,
        tabs = tabs
    )

@Composable
actual fun PrimaryScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    tabs: @Composable (() -> Unit),
) =
    PrimaryScrollableTabRow(
        selectedTabIndex,
        modifier.platformModifier,
        tabs = tabs
    )

@Composable
actual fun SecondaryScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier,
    tabs: @Composable (() -> Unit),
) =
    SecondaryScrollableTabRow(
        selectedTabIndex,
        modifier.platformModifier,
        tabs = tabs
    )
